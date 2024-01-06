package com.test.visibleonecodingtest.viewholders

import android.view.View
import com.test.visibleonecodingtest.databinding.ItemBrandBinding
import com.test.visibleonecodingtest.delegates.BrandItemDelegates
import com.test.visibleonecodingtest.models.BrandVO

class BrandItemViewHolder(
    val binding: ItemBrandBinding,
    var delegate: BrandItemDelegates,
) :
    BaseViewHolder<BrandVO>(binding.root) {

    override fun setData(data: BrandVO) {
        mData = data
    }

    override fun onClick(v: View?) {
        mData?.let { delegate.onTapBrandItem(it) }
    }
}