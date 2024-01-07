package com.test.visibleonecodingtest.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.test.visibleonecodingtest.models.BrandVO
import com.test.visibleonecodingtest.models.ShoeVO
import com.test.visibleonecodingtest.network.repository.MainRepository
import com.test.visibleonecodingtest.network.response.ShoeResponse
import com.test.visibleonecodingtest.utils.Extensions.toBrandVOList
import com.test.visibleonecodingtest.utils.Extensions.toShoeVOList
import com.test.visibleonecodingtest.utils.ViewModelHelpers.doNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoeListViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _showProgressBar = MutableLiveData(false)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _showError = MutableLiveData("")
    val showError: MutableLiveData<String> = _showError

    private val _brandList = MutableLiveData<List<BrandVO>>()
    val brandList: LiveData<List<BrandVO>> = _brandList

    private val _shoeList = MutableLiveData<List<ShoeVO>>()
    val shoeList: LiveData<List<ShoeVO>> = _shoeList

    private fun getBrandList() {
        viewModelScope.launch {
            doNetworkCall(repository.getBrandList(), _showProgressBar, _showError).collect { list ->
                _showProgressBar.postValue(false)
                if (list.isNotEmpty()) {
                    _brandList.value = list.toBrandVOList()
                    getShoeListByBrand(1)
                } else {
                    _showError.postValue("List is empty.")
                }
            }
        }
    }

    fun getShoeListByBrand(brandId: Int) {
        viewModelScope.launch {
            doNetworkCall(
                repository.getShoeList(brandId),
                _showProgressBar,
                _showError
            ).collect { response ->
                _showProgressBar.postValue(false)
                if (response.isNotEmpty()) {
                    val shoesList: List<ShoeResponse> = response.values.toList()
                    _shoeList.value = shoesList.toShoeVOList()
                } else {
                    _showError.postValue("List is empty.")
                    _shoeList.value = emptyList()
                }
            }
        }
        val updatedList = _brandList.value?.onEach {
            it.isItemSelected = it.id == brandId
        }
        _brandList.value = updatedList ?: emptyList()
    }

    init {
        getBrandList()
    }

}