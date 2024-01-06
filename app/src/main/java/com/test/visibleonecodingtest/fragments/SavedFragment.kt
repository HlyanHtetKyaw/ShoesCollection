package com.test.visibleonecodingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.activities.ShoeDetailActivity
import com.test.visibleonecodingtest.adapters.BrandItemAdapter
import com.test.visibleonecodingtest.adapters.ShoeItemAdapter
import com.test.visibleonecodingtest.databinding.FragmentSavedBinding
import com.test.visibleonecodingtest.delegates.BrandItemDelegates
import com.test.visibleonecodingtest.delegates.ShoeItemDelegates
import com.test.visibleonecodingtest.models.BrandVO

class SavedFragment : BaseFragment<FragmentSavedBinding>(), BrandItemDelegates, ShoeItemDelegates {

    private lateinit var mBrandItemAdapter: BrandItemAdapter

    private lateinit var mShoeItemAdapter: ShoeItemAdapter

    override fun initDependencies(savedInstanceState: Bundle?) {
        mBrandItemAdapter = BrandItemAdapter(this)
        mShoeItemAdapter = ShoeItemAdapter(this)
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSavedBinding {
        return FragmentSavedBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.apply {
            rvBrand.adapter = mBrandItemAdapter
            rvShoes.adapter = mShoeItemAdapter
        }

        val list: MutableList<BrandVO> = mutableListOf()
        for (i in 1..5) {
            list.add(BrandVO(i, i, i.toString()))
        }
        mBrandItemAdapter.setNewData(list)
        mShoeItemAdapter.setNewData(list)
    }

    override fun onTapItem(data: BrandVO) {
        startActivity(ShoeDetailActivity.newIntent(getContext))
    }

    override fun onTapBrandItem(data: BrandVO) {

    }

}