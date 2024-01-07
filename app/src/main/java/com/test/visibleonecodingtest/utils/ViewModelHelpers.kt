package com.test.visibleonecodingtest.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

object ViewModelHelpers {

    fun <T> doNetworkCall(
        api: Flow<T>,
        loading: MutableLiveData<Boolean>,
        error: MutableLiveData<String>
    ): Flow<T> = api.flowOn(Dispatchers.IO).onStart {
        loading.postValue(true)
    }.catch { err ->
        loading.postValue(false)
        error.postValue(err.localizedMessage)
    }
}

