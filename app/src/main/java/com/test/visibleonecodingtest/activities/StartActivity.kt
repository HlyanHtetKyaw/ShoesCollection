package com.test.visibleonecodingtest.activities

import android.os.Bundle
import com.test.visibleonecodingtest.databinding.ActivityStartBinding

class StartActivity : BaseActivity<ActivityStartBinding>() {

    override fun onCreateViewBinding() = ActivityStartBinding.inflate(layoutInflater)

    override fun initDependencies(savedInstanceState: Bundle?) {

    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.btnStart.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
        }

    }
}