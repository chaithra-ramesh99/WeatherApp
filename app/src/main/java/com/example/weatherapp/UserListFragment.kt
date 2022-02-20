package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapter.UserAdapter
import com.example.weatherapp.Respository.UserRepository
import com.example.weatherapp.databinding.FragmentUserListBinding
import com.example.weatherapp.model.User
import com.example.weatherapp.viewmodel.MyViewModelFactory
import com.example.weatherapp.viewmodel.UserViewModel


class UserListFragment : Fragment() {

    private var binding: FragmentUserListBinding? = null
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: ArrayList<User>
    lateinit var repository:UserRepository
    lateinit var myViewModelFactory: MyViewModelFactory
    private lateinit var userViewModel:UserViewModel

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        userAdapter = UserAdapter(activity!!, ArrayList())

        initRecyclerview()
        repository = UserRepository()
        myViewModelFactory = MyViewModelFactory(repository)
        userViewModel= ViewModelProvider(this,myViewModelFactory).get(UserViewModel::class.java)
        userViewModel.getAllUserData(context!!).observe(this, Observer {
           // userAdapter.setData(it as ArrayList<User>)

            userAdapter.setData(it as ArrayList<User>)
            userList = it
            Log.e("userlist:", userList.size.toString())

        })


        binding!!.AddUser.setOnClickListener {

           it.findNavController().navigate(R.id.userFormFragment)
        }


        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        return binding!!.getRoot()
    }


    private fun initRecyclerview()
    {
        binding!!.recylerviewList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity!!)
            adapter = userAdapter
            itemTouchHelper.attachToRecyclerView(binding!!.recylerviewList)

        }

    }



    private val itemTouchHelper by lazy {
        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


                val position = viewHolder.adapterPosition
                  userViewModel.deleteUser(context!!, userList[position].id)

                userList.removeAt(viewHolder.adapterPosition)
                Log.e("room", position.toString())

                userAdapter.notifyItemRemoved(position)
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)


            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.scaleY = 1.0f
                viewHolder?.itemView?.alpha = 1.0f
            }

        }
        ItemTouchHelper(itemTouchCallback)
    }



}