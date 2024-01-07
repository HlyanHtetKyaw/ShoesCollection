package com.test.visibleonecodingtest.network

import com.test.visibleonecodingtest.network.EndPoints.BRAND_LIST
import com.test.visibleonecodingtest.network.EndPoints.SHOE_LIST
import com.test.visibleonecodingtest.network.response.BrandResponse
import com.test.visibleonecodingtest.network.response.ShoeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(BRAND_LIST)
    suspend fun brandList(): MutableList<BrandResponse>

    @GET(SHOE_LIST)
    suspend fun shoeList(
        @Query("orderBy") orderBy: String = "\"brandId\"",
        @Query("equalTo") equalTo: Int
    ): Map<String, ShoeResponse>

}