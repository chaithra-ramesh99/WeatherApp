package com.example.weatherapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.LoginUser


class SignInViewModel : ViewModel() {
    var EmailAddress: MutableLiveData<String> = MutableLiveData()
    var Password: MutableLiveData<String> = MutableLiveData()

    private var userMutableLiveData: MutableLiveData<LoginUser>? = null
    val user: MutableLiveData<LoginUser>?
        get() {
            if (userMutableLiveData == null) {
                userMutableLiveData = MutableLiveData()
            }
            return userMutableLiveData
        }


    fun onClick(view: View?) {
        val loginUser = LoginUser(EmailAddress.value.toString(), Password.value.toString())
        userMutableLiveData!!.value = loginUser
    }
}