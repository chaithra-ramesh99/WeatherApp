package com.example.weatherapp.Respository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.Dao.UserDao
import com.example.weatherapp.Database.UserDatabase
import com.example.weatherapp.model.User
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRepository
{
    companion object{
    var userDatabase:UserDatabase?=null

    private fun intialiseDB(context:Context): UserDatabase?
    {
        return UserDatabase.getInstance(context)!!
    }

    fun insert(context: Context,user:User)
    {
        userDatabase= intialiseDB(context)

        CoroutineScope(IO).launch {
            userDatabase!!.userDao().insert(user)
        }
    }

        fun deleteUser(context: Context,userId:Int)
        {
            userDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().deleteUser(userId)
            }
        }

    fun getAllUserData(context: Context): LiveData<List<User>>
    {
        userDatabase= intialiseDB(context)
        return userDatabase!!.userDao().getAllUserData()
    }



}



     suspend fun getWeather(lat:String, long:String, units:String, appid:String):WeatherData
    {
        Log.e("called:",lat)

      return  RetrofitService.apiService.getWeatherData(lat, long, units, appid)


    }
}
