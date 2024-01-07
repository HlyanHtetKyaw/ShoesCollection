package com.test.visibleonecodingtest.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import com.test.visibleonecodingtest.R
import com.test.visibleonecodingtest.databinding.ItemSizeBinding
import com.test.visibleonecodingtest.delegates.ShoeSizeDelegates
import com.test.visibleonecodingtest.models.ShoeSizeVO

class ShoeSizeViewHolder(
    val binding: ItemSizeBinding,
    var delegate: ShoeSizeDelegates,
) : BaseViewHolder<ShoeSizeVO>(binding.root) {

    override fun setData(data: ShoeSizeVO) {
        mData = data
        binding.apply {
            tvSize.text = data.size
            if (data.isItemSelected) {
                cvSize.setCardBackgroundColor(
                    ContextCompat.getColor(
                        cvSize.context,
                        R.color.colorPrimary
                    )
                )
                tvSize.setTextColor(
                    ContextCompat.getColor(
                        cvSize.context,
                        R.color.white
                    )
                )
            } else {
                cvSize.setCardBackgroundColor(
                    ContextCompat.getColor(
                        cvSize.context,
                        R.color.background_gray
                    )
                )
                tvSize.setTextColor(
                    ContextCompat.getColor(
                        cvSize.context,
                        R.color.black
                    )
                )
            }
        }
    }

    override fun onClick(v: View?) {
        mData?.let { delegate.onTapShoeSize(it) }
    }
}