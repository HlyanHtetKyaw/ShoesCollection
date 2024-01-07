package com.test.visibleonecodingtest.viewholders

import android.view.View
import com.test.visibleonecodingtest.databinding.ItemSizeBinding
import com.test.visibleonecodingtest.delegates.ShoeSizeDelegates

class ShoeSizeViewHolder(
    val binding: ItemSizeBinding,
    var delegate: ShoeSizeDelegates,
) : BaseViewHolder<String>(binding.root) {

    override fun setData(data: String) {
        mData = data
        binding.apply {
            tvSize.text = data
        }
    }

    override fun onClick(v: View?) {
        mData?.let { delegate.onTapShoeSize(it) }
    }
}