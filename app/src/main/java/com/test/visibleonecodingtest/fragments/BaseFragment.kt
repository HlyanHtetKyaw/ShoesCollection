package com.test.visibleonecodingtest.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null

    // This property is only valid between onCreateView and onDestroyView.
    val binding get() = _binding!!

    private var _activity: Activity? = null
    private var _context: Context? = null

    protected val activity get() = _activity!!
    protected val getContext get() = _context!!

    abstract fun initDependencies(savedInstanceState: Bundle?)

    abstract fun initViews(savedInstanceState: Bundle?)

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = onCreateViewBinding(inflater, container)
        _activity = requireActivity()
        _context = requireContext()
        initDependencies(savedInstanceState)
        initViews(savedInstanceState)
        return binding.root
    }

    protected abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _activity = null
        _context = null
    }
}