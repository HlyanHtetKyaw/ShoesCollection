package com.test.visibleonecodingtest.activities

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.test.visibleonecodingtest.R
import com.test.visibleonecodingtest.adapters.ShoeSizeAdapter
import com.test.visibleonecodingtest.databinding.ActivityShoeDetailBinding
import com.test.visibleonecodingtest.delegates.ShoeSizeDelegates
import com.test.visibleonecodingtest.models.ShoeSizeVO
import com.test.visibleonecodingtest.utils.Extensions
import com.test.visibleonecodingtest.viewModels.ShoeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoeDetailActivity : BaseActivity<ActivityShoeDetailBinding>(), ShoeSizeDelegates {

    private lateinit var mShoeSizeAdapter: ShoeSizeAdapter

    override fun onCreateViewBinding() = ActivityShoeDetailBinding.inflate(layoutInflater)

    override fun initDependencies(savedInstanceState: Bundle?) {
    }

    private val shoeDetailViewModel by viewModels<ShoeDetailViewModel>()

    companion object {
        private var id: Int = 0
        fun newIntent(context: Context, id: Int): Intent {
            this.id = id
            return Intent(context, ShoeDetailActivity::class.java)
        }
    }

    override fun initViews(savedInstanceState: Bundle?) {
        shoeDetailViewModel.apply {
            getShoeDetail(id)
            getShoeSizeList(1)

            showProgressBar.observe(this@ShoeDetailActivity) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
            showError.observe(this@ShoeDetailActivity) {
                if (it.isNotEmpty()) {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                }
            }
            shoeDetail.observe(this@ShoeDetailActivity) {
                binding.progressBar.visibility = View.GONE
                with(binding) {
                    tvName.text = it.name
                    tvPrice.text = it.price
                    tvCategory.text = it.category
                    Glide.with(binding.ivShoe.context)
                        .load(Extensions.getImageUrlFromFirebase(it.imageUrl))
                        .placeholder(R.drawable.logo_nike)
                        .error(R.drawable.logo_nike).into(binding.ivShoe)
                }
            }
            shoeSizeList.observe(this@ShoeDetailActivity) {
                mShoeSizeAdapter.setNewData(it.toMutableList())
            }
        }

        binding.apply {
            mShoeSizeAdapter = ShoeSizeAdapter(this@ShoeDetailActivity)
            rvSize.adapter = mShoeSizeAdapter
            setupDifferentSizes(1)
            tvUs.setOnClickListener { setupDifferentSizes(1) }
            tvUk.setOnClickListener { setupDifferentSizes(2) }
            tvEu.setOnClickListener { setupDifferentSizes(3) }
            tvPlus.setOnClickListener {
                val quantity = tvQuantity.text.toString().toInt() + 1
                tvQuantity.text = quantity.toString()
            }
            tvMinus.setOnClickListener {
                val quantity = tvQuantity.text.toString().toInt() - 1
                if (quantity > 0) {
                    tvQuantity.text = quantity.toString()
                } else {
                    Toast.makeText(
                        this@ShoeDetailActivity,
                        "Item size cannot be less than zero",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            ivBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            tvDescription.visibility = View.GONE
            ivMoreDescription.setOnClickListener { toggleVisibilityForDescription() }

            tvDelivery.visibility = View.GONE
            ivMoreDelivery.setOnClickListener { toggleVisibilityForDelivery() }
        }
    }

    private fun setupDifferentSizes(type: Int) {
        binding.apply {
            when (type) {
                1 -> {
                    tvUs.setTypeface(tvUs.typeface, Typeface.BOLD)
                    tvUk.setTypeface(null, Typeface.NORMAL)
                    tvEu.setTypeface(null, Typeface.NORMAL)
                }

                2 -> {
                    tvUs.setTypeface(null, Typeface.NORMAL)
                    tvUk.setTypeface(tvUk.typeface, Typeface.BOLD)
                    tvEu.setTypeface(null, Typeface.NORMAL)
                }

                3 -> {
                    tvUs.setTypeface(null, Typeface.NORMAL)
                    tvUk.setTypeface(null, Typeface.NORMAL)
                    tvEu.setTypeface(tvEu.typeface, Typeface.BOLD)
                }

                else -> {
                    tvUs.setTypeface(tvUs.typeface, Typeface.BOLD)
                    tvUk.setTypeface(null, Typeface.NORMAL)
                    tvEu.setTypeface(null, Typeface.NORMAL)
                }
            }
        }
        shoeDetailViewModel.getShoeSizeList(type)
    }

    override fun onTapShoeSize(data: ShoeSizeVO) {
        shoeDetailViewModel.changeShoeSize(data.id)
    }


    private fun toggleVisibilityForDescription() {
        val targetHeight =
            if (binding.tvDescription.visibility == View.VISIBLE) 0 else getContentHeight()
        val animator = ValueAnimator.ofInt(binding.clDescription.height, targetHeight)
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            val layoutParams = binding.clDescription.layoutParams
            layoutParams.height = value
            binding.clDescription.layoutParams = layoutParams
        }

        animator.duration = 300
        animator.start()

        // Toggle visibility after animation
        binding.tvDescription.visibility = if (targetHeight == 0) View.GONE else View.VISIBLE

        val downArrow =
            ContextCompat.getDrawable(this@ShoeDetailActivity, R.drawable.ic_down_arrow)
        val upArrow = ContextCompat.getDrawable(this@ShoeDetailActivity, R.drawable.ic_up_arrow)
        if (binding.tvDescription.visibility == View.VISIBLE) {
            binding.ivMoreDescription.setImageDrawable(upArrow)
        } else {
            binding.ivMoreDescription.setImageDrawable(downArrow)
        }
    }

    private fun toggleVisibilityForDelivery() {
        val targetHeight =
            if (binding.tvDelivery.visibility == View.VISIBLE) 0 else getContentHeight()
        val animator = ValueAnimator.ofInt(binding.clDelivery.height, targetHeight)
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            val layoutParams = binding.clDelivery.layoutParams
            layoutParams.height = value
            binding.clDelivery.layoutParams = layoutParams
        }

        animator.duration = 300
        animator.start()

        // Toggle visibility after animation
        binding.tvDelivery.visibility = if (targetHeight == 0) View.GONE else View.VISIBLE

        val downArrow =
            ContextCompat.getDrawable(this@ShoeDetailActivity, R.drawable.ic_down_arrow)
        val upArrow = ContextCompat.getDrawable(this@ShoeDetailActivity, R.drawable.ic_up_arrow)
        if (binding.tvDelivery.visibility == View.VISIBLE) {
            binding.ivMoreDelivery.setImageDrawable(upArrow)
        } else {
            binding.ivMoreDelivery.setImageDrawable(downArrow)
        }
    }

    private fun getContentHeight(): Int {
        // Measure the height of the content when it's visible +100 for margin
        binding.tvDescription.measure(
            View.MeasureSpec.makeMeasureSpec(binding.clDescription.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.UNSPECIFIED
        )
        return binding.tvDescription.measuredHeight + 100
    }
}
