package com.test.visibleonecodingtest.viewholders

import android.view.View
import com.test.visibleonecodingtest.databinding.ItemShoeBinding
import com.test.visibleonecodingtest.delegates.ShoeItemDelegates
import com.test.visibleonecodingtest.models.BrandVO

class ShoeItemViewHolder(
    val binding: ItemShoeBinding,
    var delegate: ShoeItemDelegates,
) :
    BaseViewHolder<BrandVO>(binding.root) {

    override fun setData(data: BrandVO) {
        mData = data
    }

    override fun onClick(v: View?) {
        mData?.let { delegate.onTapItem(it) }
    }
}