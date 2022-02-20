package com.example.weatherapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Respository.UserRepository
import com.example.weatherapp.model.User
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(private  val repository: UserRepository) :ViewModel() {

    val weatherData = MutableLiveData<WeatherData>()
    fun insert(context: Context,user:User)
    {
        UserRepository.insert(context,user)
    }

    fun getAllUserData(context: Context):LiveData<List<User>>
    {
        return UserRepository.getAllUserData(context)
    }

    fun deleteUser(context: Context, userId: Int?) {
        UserRepository.deleteUser(context , userId!!)
    }



    var lat:String?="12.9082847623315"
    var long:String?="77.65197822993314"
    var units:String?="imperial"
    var appid:String?="b143bb707b2ee117e62649b358207d3e"
    val myResponse:MutableLiveData<WeatherData> = MutableLiveData()

    fun getWeather() {


        viewModelScope.launch{
            try{
                val response = repository.getWeather(lat!!,long!!,units!!,appid!! )
                myResponse.value = response

            }catch (e:Exception){
                Log.e("response:", "getPost: ${e.message}")
            }
        }
    }

}