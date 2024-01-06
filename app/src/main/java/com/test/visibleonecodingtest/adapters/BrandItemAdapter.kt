package com.test.visibleonecodingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.ItemBrandBinding
import com.test.visibleonecodingtest.delegates.BrandItemDelegates
import com.test.visibleonecodingtest.models.BrandVO
import com.test.visibleonecodingtest.viewholders.BaseViewHolder
import com.test.visibleonecodingtest.viewholders.BrandItemViewHolder

class BrandItemAdapter(var delegate: BrandItemDelegates) :
    BaseAdapter<BrandItemViewHolder, BrandVO>() {

    private var _binding: ItemBrandBinding? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<BrandVO> {
        _binding =
            ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandItemViewHolder(_binding!!, delegate)
    }

}