package com.test.visibleonecodingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.FragmentCartBinding

class CartFragment : BaseFragment<FragmentCartBinding>() {

    override fun initDependencies(savedInstanceState: Bundle?) {}

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {}

}