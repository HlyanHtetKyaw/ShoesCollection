package com.test.visibleonecodingtest.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.test.visibleonecodingtest.R
import com.test.visibleonecodingtest.databinding.ItemShoeBinding
import com.test.visibleonecodingtest.delegates.ShoeItemDelegates
import com.test.visibleonecodingtest.models.ShoeVO

class ShoeItemViewHolder(
    val binding: ItemShoeBinding,
    var delegate: ShoeItemDelegates,
) :
    BaseViewHolder<ShoeVO>(binding.root) {

    override fun setData(data: ShoeVO) {
        mData = data
        binding.apply {
            tvName.text = data.name
            tvPrice.text = data.price
            val imageUrl =
                "https://firebasestorage.googleapis.com/v0/b/shoescollection-821f8.appspot.com/o/${data.imageUrl}?alt=media"
            Glide.with(binding.ivShoe.context)
                .load(imageUrl)
                .placeholder(R.drawable.logo_nike)
                .error(R.drawable.logo_nike).into(binding.ivShoe)
        }
    }

    override fun onClick(v: View?) {
        mData?.let { delegate.onTapItem(it) }
    }
}