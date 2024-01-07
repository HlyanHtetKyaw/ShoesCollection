package com.test.visibleonecodingtest.network.response

data class ShoeResponse(
    val brandId: Int,
    val category: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: String
)