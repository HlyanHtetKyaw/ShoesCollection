package com.test.visibleonecodingtest.activities

import android.app.Activity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.test.visibleonecodingtest.R

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    abstract fun initDependencies(savedInstanceState: Bundle?)

    abstract fun initViews(savedInstanceState: Bundle?)

    private lateinit var _binding: T

    // This property is only valid after onCreate
    val binding get() = _binding

    private var _activity: Activity? = null

    protected val activity get() = _activity!!

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateWindowTransition(window)
        super.onCreate(savedInstanceState)
        _binding = onCreateViewBinding()
        _activity = this
        setContentView(_binding.root)
        initViews(savedInstanceState)
        initDependencies(savedInstanceState)
    }

    /**
     * @deprecated don't inflate layoutResID. Use binding property instead.
     */
    @Deprecated(
        "don't inflate layoutResID. Use binding property instead.",
        level = DeprecationLevel.ERROR
    )
    final override fun setContentView(layoutResID: Int) {
    }

    protected open fun onCreateWindowTransition(window: Window) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        val enter = MaterialSharedAxis(MaterialSharedAxis.Y, true).apply {
            addTarget(R.id.root)
        }
        window.enterTransition = enter
        val `return` = MaterialSharedAxis(MaterialSharedAxis.Y, false).apply {
            addTarget(R.id.root)
        }
        window.returnTransition = `return`
        // Allow Activity A’s exit transition to play at the same time as this Activity’s
        // enter transition instead of playing them sequentially.
        window.allowEnterTransitionOverlap = true
    }

    protected abstract fun onCreateViewBinding(): T
}