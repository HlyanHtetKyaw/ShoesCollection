package com.test.visibleonecodingtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.visibleonecodingtest.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun initDependencies(savedInstanceState: Bundle?) {}

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {}

}