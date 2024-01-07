package com.test.visibleonecodingtest.delegates

import com.test.visibleonecodingtest.models.ShoeVO

interface ShoeItemDelegates {
    fun onTapItem(data: ShoeVO)
}