package com.example.weatherapp.retrofit

import com.example.weatherapp.model.WeatherData
import com.guard.securitysei.retrofit.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitService {

    private const val BASE_URL = "https://api.openweathermap.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: Api = getRetrofit().create(Api::class.java)
}