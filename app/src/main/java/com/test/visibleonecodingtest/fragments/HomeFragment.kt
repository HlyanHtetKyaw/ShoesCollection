package com.test.visibleonecodingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initDependencies(savedInstanceState: Bundle?) {}

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {}

}