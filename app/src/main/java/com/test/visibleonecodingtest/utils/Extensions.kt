package com.test.visibleonecodingtest.utils

import com.test.visibleonecodingtest.models.BrandVO
import com.test.visibleonecodingtest.models.ShoeVO
import com.test.visibleonecodingtest.network.response.BrandResponse
import com.test.visibleonecodingtest.network.response.ShoeResponse

object Extensions {

    private fun BrandResponse.toBrandVO() = BrandVO(
        id = id,
        name = name,
        logo = logo
    )

    fun List<BrandResponse>.toBrandVOList(): List<BrandVO> {
        return this.map { it.toBrandVO() }
    }

    fun ShoeResponse.toShoeVO() = ShoeVO(
        id = id,
        name = name,
        imageUrl = imageUrl,
        price = price,
        category = category
    )

    fun List<ShoeResponse>.toShoeVOList(): List<ShoeVO> {
        return this.map { it.toShoeVO() }
    }

    fun getImageUrlFromFirebase(name: String) =
        "https://firebasestorage.googleapis.com/v0/b/shoescollection-821f8.appspot.com/o/$name?alt=media"

}