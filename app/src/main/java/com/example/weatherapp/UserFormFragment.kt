package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PointF.length
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.Respository.UserRepository
import com.example.weatherapp.container.ImageBitmapString
import com.example.weatherapp.databinding.FragmentUserFormBinding
import com.example.weatherapp.model.User
import com.example.weatherapp.viewmodel.MyViewModelFactory
import com.example.weatherapp.viewmodel.UserViewModel
import java.io.File


open class UserFormFragment : Fragment() {





    private var binding: FragmentUserFormBinding? = null
    lateinit var repository: UserRepository
    lateinit var myViewModelFactory: MyViewModelFactory
    private lateinit var userViewModel:UserViewModel

    private val REQUEST_CODE = 1888
     var bitmap:Bitmap?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserFormBinding.inflate(inflater, container, false)


        repository = UserRepository()
        myViewModelFactory = MyViewModelFactory(repository)
        userViewModel= ViewModelProvider(this,myViewModelFactory).get(UserViewModel::class.java)



        binding!!.btncancel.setOnClickListener {

         it.findNavController().navigate(R.id.action_userFormFragment_to_userListFragment2)

        }

        binding!!.uploadBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }

        val edemail = binding!!.edemail.text.toString().trim()
        val edname = binding!!.edname.text.toString().trim()
        val edlname = binding!!.edlname.text.toString().trim()


        binding!!.btnsave.setOnClickListener {
            if(edemail.isEmpty()&& edlname.isEmpty() && edname.isEmpty() &&  bitmap==null)
            {

                Toast.makeText(context!!,"Plz select the image and fill missing fields",Toast.LENGTH_SHORT).show()

            }

            else{
                insertImages()

            }
        }

        return binding!!.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){

            binding!!.image.setImageURI(data?.data) // handle chosen image
            var uri:Uri = data?.data!!



             bitmap = MediaStore.Images.Media.getBitmap(context!!.getContentResolver(), uri)
            binding!!.image.setImageBitmap(bitmap)





        }
    }




    private fun insertImages() {
        var name = binding?.edname?.text.toString()
        var lname = binding?.edlname?.text.toString()
        var email = binding?.edemail?.text.toString()



        val user = User(name,lname,email,ImageBitmapString.getStringFromBitmap(bitmap!!))
        userViewModel.insert(context!!,user)

        Toast.makeText(activity, "Data Saved", Toast.LENGTH_SHORT).show()
       findNavController().navigate(R.id.action_userFormFragment_to_userListFragment2)


    }



}