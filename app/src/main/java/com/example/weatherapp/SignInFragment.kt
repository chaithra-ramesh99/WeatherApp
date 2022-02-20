package com.example.weatherapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.Database.UserManager
import com.example.weatherapp.databinding.FragmentSignInBinding
import com.example.weatherapp.model.LoginUser
import com.example.weatherapp.viewmodel.SignInViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class SignInFragment : Fragment() {
    private var loginViewModel: SignInViewModel? = null

    lateinit var binding: FragmentSignInBinding
    lateinit var userManager: UserManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater!!, container, false)

       loginViewModel = ViewModelProviders.of(this)[SignInViewModel::class.java]

       binding.signInViewModel = loginViewModel


       loginViewModel!!.user!!.observe(this
       ) { loginUser ->
           if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strEmailAddress)) {
               binding.txtEmailAddress.error = "Enter an E-Mail Address"
               binding.txtEmailAddress.requestFocus()
           } else if (!loginUser!!.isEmailValid) {
               binding.txtEmailAddress.error = "Enter a Valid E-mail Address"
               binding.txtEmailAddress.requestFocus()
           } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).strPassword)) {
               binding.txtPassword.error = "Enter a Password"
               binding.txtPassword.requestFocus()
           } else if (!loginUser!!.isPasswordLengthGreaterThan5) {
               binding.txtPassword.error = "Enter at least 6 Digit password"
               binding.txtPassword.requestFocus()
           } else if (binding.txtEmailAddress.text.toString() == "testapp@google.com"
               && binding.txtPassword.text.toString() == "Test@123456"
           ) {

               userManager = UserManager(context!!)
               GlobalScope.launch {
                   userManager.storeUser(binding.txtEmailAddress.text.toString())
                   Log.e("isloged1:", binding.txtEmailAddress.text.toString())

               }
               findNavController().navigate(R.id.action_signInFragment_to_userListFragment2)

           } else {

               Toast.makeText(context, "Email id or password do not match ", Toast.LENGTH_SHORT).show()
           }
       }
        return binding.root
    }




}