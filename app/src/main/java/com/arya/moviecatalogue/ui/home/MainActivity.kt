package com.arya.moviecatalogue.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie, R.id.navigation_tv, R.id.navigation_favorite
            )
        )

        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navHostFragment.navController)
    }

}