package com.test.visibleonecodingtest.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.visibleonecodingtest.models.ShoeSizeVO
import com.test.visibleonecodingtest.models.ShoeVO
import com.test.visibleonecodingtest.network.repository.MainRepository
import com.test.visibleonecodingtest.network.response.ShoeResponse
import com.test.visibleonecodingtest.utils.Extensions.toShoeVO
import com.test.visibleonecodingtest.utils.ViewModelHelpers.doNetworkCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoeDetailViewModel @Inject constructor(private val repository: MainRepository) :
    ViewModel() {

    private val _showProgressBar = MutableLiveData(false)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private val _showError = MutableLiveData("")
    val showError: MutableLiveData<String> = _showError

    private val _shoeDetail = MutableLiveData<ShoeVO>()
    val shoeDetail: LiveData<ShoeVO> = _shoeDetail

    private val _shoeSizeList = MutableLiveData<List<ShoeSizeVO>>()
    val shoeSizeList: LiveData<List<ShoeSizeVO>> = _shoeSizeList

    fun getShoeDetail(id: Int) {
        viewModelScope.launch {
            doNetworkCall(
                repository.getSheDetail(id),
                _showProgressBar,
                _showError
            ).collect { response ->
                _showProgressBar.postValue(false)
                if (response.isNotEmpty()) {
                    val shoesList: List<ShoeResponse> = response.values.toList()
                    if (shoesList.isNotEmpty()) {
                        _shoeDetail.value = shoesList[0].toShoeVO()
                    }
                } else {
                    _showError.postValue("List is empty.")
                }
            }
        }
    }

    fun changeShoeSize(id: Int) {
        val updatedList = _shoeSizeList.value?.onEach {
            it.isItemSelected = it.id == id
        }
        _shoeSizeList.value = updatedList ?: emptyList()
    }

    fun getShoeSizeList(type: Int) {
        val list = mutableListOf<ShoeSizeVO>()
        for (i in 1..5) {
            if (type == 1) {
                if (i == 2) {
                    list.add(ShoeSizeVO(i, (5 + i.toDouble() / 2).toString(), true))
                } else {
                    list.add(ShoeSizeVO(i, (5 + i.toDouble() / 2).toString()))
                }
            } else {
                if (i == 2) {
                    list.add(ShoeSizeVO(i, (35 + i).toString(), true))
                } else {
                    list.add(ShoeSizeVO(i, (35 + i).toString()))
                }
            }
        }
        _shoeSizeList.value = list
    }

}