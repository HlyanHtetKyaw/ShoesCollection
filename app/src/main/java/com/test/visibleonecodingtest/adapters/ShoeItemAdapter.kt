package com.test.visibleonecodingtest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.ItemShoeBinding
import com.test.visibleonecodingtest.delegates.ShoeItemDelegates
import com.test.visibleonecodingtest.models.BrandVO
import com.test.visibleonecodingtest.models.ShoeVO
import com.test.visibleonecodingtest.viewholders.BaseViewHolder
import com.test.visibleonecodingtest.viewholders.ShoeItemViewHolder

class ShoeItemAdapter(var delegate: ShoeItemDelegates) :
    BaseAdapter<ShoeItemViewHolder, ShoeVO>() {

    private var _binding: ItemShoeBinding? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ShoeVO> {
        _binding =
            ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeItemViewHolder(_binding!!, delegate)
    }

}