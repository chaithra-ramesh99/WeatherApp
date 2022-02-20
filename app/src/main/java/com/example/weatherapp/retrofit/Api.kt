package com.guard.securitysei.retrofit

import com.example.weatherapp.model.WeatherData
import retrofit2.Response

import retrofit2.http.*


interface Api
{






    @GET("data/2.5/onecall")
    suspend fun getWeatherData
    (
    @Query("lat") lat:String,
    @Query("lon") lon:String,
    @Query("units") units:String,
    @Query("appid") appid:String): WeatherData




}