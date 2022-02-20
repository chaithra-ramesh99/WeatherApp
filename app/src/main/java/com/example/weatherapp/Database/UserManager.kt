package com.example.weatherapp.Database

import android.content.Context
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
    }

    suspend fun storeUser( name: String) {
        dataStore.edit {
            it[USER_NAME_KEY] = name

        }
    }



    val userNameFlow: Flow<String?>
    get()= dataStore.data.map {value: Preferences ->
       value [USER_NAME_KEY]
    }
}