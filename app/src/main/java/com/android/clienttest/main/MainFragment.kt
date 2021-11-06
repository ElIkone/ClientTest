package com.android.clienttest.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.clienttest.R
import com.android.clienttest.databinding.FragmentMainBinding
import com.android.clienttest.model.RandomUser
import com.android.clienttest.network.NetworkRepository
import com.android.clienttest.network.RetrofitService

class MainFragment : Fragment(), UserAdapter.UserItemListener {
    private lateinit var binding: FragmentMainBinding
    private val adapter = UserAdapter(this)
    private val retrofitService = RetrofitService.getInstance()
    private val mainRepository = NetworkRepository(retrofitService)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onClickedUser(userObject: RandomUser) {
        TODO("Not yet implemented")
    }
}