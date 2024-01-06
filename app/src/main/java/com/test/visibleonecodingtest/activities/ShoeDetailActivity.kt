package com.test.visibleonecodingtest.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.test.visibleonecodingtest.databinding.ActivityShoeDetailBinding


class ShoeDetailActivity : BaseActivity<ActivityShoeDetailBinding>() {

    override fun onCreateViewBinding() = ActivityShoeDetailBinding.inflate(layoutInflater)

    override fun initDependencies(savedInstanceState: Bundle?) {

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ShoeDetailActivity::class.java)
        }
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.apply {

        }

    }
}