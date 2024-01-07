package com.test.visibleonecodingtest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.test.visibleonecodingtest.activities.ShoeDetailActivity
import com.test.visibleonecodingtest.adapters.BrandItemAdapter
import com.test.visibleonecodingtest.adapters.ShoeItemAdapter
import com.test.visibleonecodingtest.databinding.FragmentSavedBinding
import com.test.visibleonecodingtest.delegates.BrandItemDelegates
import com.test.visibleonecodingtest.delegates.ShoeItemDelegates
import com.test.visibleonecodingtest.models.BrandVO
import com.test.visibleonecodingtest.models.ShoeVO
import com.test.visibleonecodingtest.viewModels.ShoeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding>(), BrandItemDelegates, ShoeItemDelegates {

    private lateinit var mBrandItemAdapter: BrandItemAdapter

    private lateinit var mShoeItemAdapter: ShoeItemAdapter

    private val shoeListViewModel by viewModels<ShoeListViewModel>()

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
        shoeListViewModel.apply {
            showProgressBar.observe(this@SavedFragment) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
            brandList.observe(this@SavedFragment) {
                binding.progressBar.visibility = View.GONE
                mBrandItemAdapter.setNewData(it.toMutableList())
            }
            showError.observe(this@SavedFragment) {
                if (it.isNotEmpty()) {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                }
            }
            shoeList.observe(this@SavedFragment) {
                binding.progressBar.visibility = View.GONE
                mShoeItemAdapter.setNewData(it.toMutableList())
            }

        }

    }

    override fun onTapItem(data: ShoeVO) {
        startActivity(ShoeDetailActivity.newIntent(getContext))
    }

    override fun onTapBrandItem(data: BrandVO) {
        shoeListViewModel.getShoeListByBrand(data.id)
    }

}