package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.Database.SharePref
import com.example.weatherapp.Database.UserManager
import com.example.weatherapp.viewmodel.SplashState
import com.example.weatherapp.viewmodel.SplashViewModel
import kotlinx.coroutines.delay



class SplashFragment : Fragment() {
     lateinit var userManager:UserManager
    var name = ""
    lateinit var sharePref:SharePref

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharePref = SharePref.instance!!;
        sharePref.setContext(context!!);


        val splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        splashViewModel.liveData.observe(this, Observer {
            when (it) {

                is SplashState.MainActivity -> {
                    goToMainActivity()
                }


            }
        })

    }

    private fun goToMainActivity() {

        if(  sharePref.mail==null)

            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)

        else

           findNavController().navigate(R.id.action_splashFragment_to_userListFragment2)

    }

    override fun onDestroy() {
        super.onDestroy()
    }



}