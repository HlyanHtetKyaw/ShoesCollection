package com.test.visibleonecodingtest.delegates

import com.test.visibleonecodingtest.models.BrandVO

interface BrandItemDelegates {
    fun onTapBrandItem(data: BrandVO)
}