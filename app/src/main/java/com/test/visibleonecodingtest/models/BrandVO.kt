package com.test.visibleonecodingtest.models

data class BrandVO(
    var id: Int,
    var logo: String,
    var name: String,
    var isItemSelected: Boolean = false
)