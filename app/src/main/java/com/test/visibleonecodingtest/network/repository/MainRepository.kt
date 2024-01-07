package com.test.visibleonecodingtest.network.repository

import com.test.visibleonecodingtest.network.ApiService
import kotlinx.coroutines.flow.flow

class MainRepository(private val apiService: ApiService) {

    suspend fun getBrandList() = flow { emit(apiService.brandList()) }

    suspend fun getShoeList(brandId: Int) = flow { emit(apiService.shoeList(equalTo = brandId)) }

    suspend fun getSheDetail(id: Int) = flow { emit(apiService.shoeDetail(equalTo = id)) }
}