package com.test.visibleonecodingtest.viewholders

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.test.visibleonecodingtest.R
import com.test.visibleonecodingtest.databinding.ItemBrandBinding
import com.test.visibleonecodingtest.delegates.BrandItemDelegates
import com.test.visibleonecodingtest.models.BrandVO

class BrandItemViewHolder(
    val binding: ItemBrandBinding,
    var delegate: BrandItemDelegates,
) : BaseViewHolder<BrandVO>(binding.root) {

    override fun setData(data: BrandVO) {
        mData = data
        val imageUrl =
            "https://firebasestorage.googleapis.com/v0/b/shoescollection-821f8.appspot.com/o/${data.logo}?alt=media"
        Glide.with(binding.ivBrand.context)
            .load(imageUrl)
            .placeholder(R.drawable.logo_nike)
            .error(R.drawable.logo_nike).into(binding.ivBrand)
        if (data.isItemSelected) {
            ImageViewCompat.setImageTintList(
                binding.ivBrand, ColorStateList.valueOf(
                    ContextCompat.getColor(binding.ivBrand.context, R.color.black)
                )
            )
        } else {
            ImageViewCompat.setImageTintList(
                binding.ivBrand, ColorStateList.valueOf(
                    ContextCompat.getColor(binding.ivBrand.context, R.color.gray_200)
                )
            )
        }
    }

    override fun onClick(v: View?) {
        mData?.let {
            delegate.onTapBrandItem(it)
        }
    }
}