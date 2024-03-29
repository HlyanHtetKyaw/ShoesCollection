package com.test.visibleonecodingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.ItemSizeBinding
import com.test.visibleonecodingtest.delegates.ShoeSizeDelegates
import com.test.visibleonecodingtest.models.ShoeSizeVO
import com.test.visibleonecodingtest.viewholders.BaseViewHolder
import com.test.visibleonecodingtest.viewholders.ShoeSizeViewHolder

class ShoeSizeAdapter(var delegate: ShoeSizeDelegates) :
    BaseAdapter<ShoeSizeViewHolder, ShoeSizeVO>() {

    private var _binding: ItemSizeBinding? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ShoeSizeVO> {
        _binding =
            ItemSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeSizeViewHolder(_binding!!, delegate)
    }

}