package com.test.visibleonecodingtest.network

import com.test.visibleonecodingtest.network.EndPoints.SHOES_LIST
import retrofit2.http.GET

interface ApiService {

    @GET(SHOES_LIST)
    suspend fun getShoesList()

}