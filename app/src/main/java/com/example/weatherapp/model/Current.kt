package com.example.weatherapp.model

data class Current (
    var temp: Double,
    var humidity: Double,
    var wind_speed: Double,
    val weather:ArrayList<Weather>)
{

}