package com.test.visibleonecodingtest.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.test.visibleonecodingtest.R
import com.test.visibleonecodingtest.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreateViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initDependencies(savedInstanceState: Bundle?) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        binding.apply {
            toggle = ActionBarDrawerToggle(
                activity, drawerLayout,
                R.string.open, R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            bottomNavView.itemIconTintList = null
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            setupWithNavController(bottomNavView, navController)
            setupWithNavController(drawerNavView, navController)
            drawerNavView.itemIconTintList = null
            configureToolbar()
        }
    }


    private fun configureToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setDisplayShowTitleEnabled(false)
        actionbar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolbar_item_cart -> {
                    navController.navigate(R.id.cartFragment)
                }
            }
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

}