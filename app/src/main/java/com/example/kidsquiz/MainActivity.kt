package com.example.kidsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.kidsquiz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mNavController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        mNavController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // val appBarConfiguration = AppBarConfiguration(
        //     setOf(R.id.provideDetailsFragment, R.id.multiPlayerFragment)
        //)
        val bottom_nav_view = binding.bottomNavigationView
        setupWithNavController(bottom_nav_view, mNavController)

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->

                when (destination.id) {
                    R.id.startFragment, R.id.multiPlayerFragment, R.id.singlePlayerFragment, R.id.settingsFragment ->
                        bottom_nav_view.visibility = View.VISIBLE
                    else -> bottom_nav_view.visibility = View.GONE
                }

            }

    }
}