package com.example.dog_observer

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dog_observer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityVeiwModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        setupNavigation()
        val navController = navHostFragment.navController
       // navController.navigate(R.id.action_loginFragment_to_dogListFragment)
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dogListFragment,
                R.id.favouritesFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.favouritesFragment -> {
                    Toast.makeText(this, viewModel.str, Toast.LENGTH_LONG).show()
                    supportActionBar?.hide()
            }
                else -> supportActionBar?.show()
            }
        }
        binding.bottomNavigationView.setupWithNavController(navController)
    }

//    private fun observeViewModel()
//    {
//        viewModel..navigation.observe(this)
//        {
//            findNavController(R.id.nav_host_fragment).navigate(it)
//        }
//    }
}