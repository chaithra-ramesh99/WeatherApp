package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var nav_host_fragment:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        nav_host_fragment = this.findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.userListFragment2,R.id.signInFragment,R.id.splashFragment

            )
        )

        setupActionBarWithNavController(nav_host_fragment!!, appBarConfiguration!!)
      //  NavigationUI.setupActionBarWithNavController(this,nav_host_fragment)

    }


    override fun onSupportNavigateUp(): Boolean {
        return nav_host_fragment.navigateUp()
    }

}