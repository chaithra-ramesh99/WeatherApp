package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.Respository.UserRepository
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.viewmodel.MyViewModelFactory
import com.example.weatherapp.viewmodel.UserViewModel

class WeatherFragment : Fragment() {

    private var binding: FragmentWeatherBinding? = null
    lateinit var repository:UserRepository
    lateinit var myViewModelFactory: MyViewModelFactory
    private lateinit var userList: WeatherData

    private lateinit var
            userViewModel:UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentWeatherBinding.inflate(inflater, container, false)


        repository = UserRepository()
        myViewModelFactory = MyViewModelFactory(repository)
        userViewModel= ViewModelProvider(this,myViewModelFactory).get(UserViewModel::class.java)
        userViewModel.getWeather()
        userViewModel.myResponse.observe(this) { data -> setData(data) }

        userViewModel.myResponse.observe(this, Observer {
            Log.e("TEMP:", it.current.humidity.toString())
            Log.e("TEMP1:", it.current.temp.toString())

           // setPostData(it as ArrayList<Post>)



        })
        binding!!.logout.setOnClickListener {
          it.findNavController().navigate(R.id.action_weatherFragment_to_signInFragment)

        }

        return binding!!.getRoot()
    }

    fun setData(data: WeatherData)
    {
        binding!!.temp.text ="Temperature:"+ data.current.temp.toString()
        binding!!.wtype.text = "Weather Type:"+data.current.weather[0].description
        binding!!.humdity.text ="Humidity:"+ data.current.humidity.toString()
        binding!!.winspeed.text ="Wind Speed:"+ data.current.wind_speed.toString()
    }


}