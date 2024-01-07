package com.test.visibleonecodingtest.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}