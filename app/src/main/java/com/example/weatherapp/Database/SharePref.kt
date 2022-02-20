package com.example.weatherapp.Database

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.Database.SharePref

class SharePref {
    private var preferences: SharedPreferences? = null
    fun setContext(context: Context) {
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    }

    fun setUser(user: String) {
        setMail(user)
    }

    val mail: String?
        get() = preferences!!.getString("loggedIn", null)

    private fun setMail(mail: String) {
        val editor = preferences!!.edit()
        editor.putString("loggedIn", mail)
        editor.apply()
    }

    companion object {
        private var singleTon: SharePref? = null
        val instance: SharePref?
            get() {
                if (singleTon == null) {
                    singleTon = SharePref()
                }
                return singleTon
            }
    }
}