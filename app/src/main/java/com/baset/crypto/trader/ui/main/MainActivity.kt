package com.baset.crypto.trader.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.baset.crypto.trader.R
import com.baset.crypto.trader.databinding.ActivityMainBinding
import com.baset.crypto.trader.di.app.findAppComponent
import com.baset.crypto.trader.di.main.DaggerMainComponent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDagger()
        setupBinding()
        setupNavController()
    }

    private fun setupDagger() {
        DaggerMainComponent.builder().appComponent(findAppComponent()).build().inject(this)
    }


    private fun setupBinding() {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        setContentView(binding.root)
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_app) as NavHostFragment
        navController = navHostFragment.navController
    }
}