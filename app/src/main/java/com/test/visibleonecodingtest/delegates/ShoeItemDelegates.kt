package com.test.visibleonecodingtest.delegates

import com.test.visibleonecodingtest.models.BrandVO

interface ShoeItemDelegates {
    fun onTapItem(data: BrandVO)
}