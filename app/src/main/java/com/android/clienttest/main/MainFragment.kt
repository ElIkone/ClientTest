package com.android.clienttest.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.clienttest.R
import com.android.clienttest.databinding.FragmentMainBinding
import com.android.clienttest.model.RandomUser
import com.android.clienttest.network.NetworkRepository
import com.android.clienttest.network.RetrofitService
import com.android.clienttest.utils.SimpleDividerItemDecoration
import com.android.clienttest.viewModel.UsersViewModel
import com.android.clienttest.viewModel.UsersViewModelFactory



class MainFragment : Fragment() {
    lateinit var viewModel: UsersViewModel
    private lateinit var binding: FragmentMainBinding
        private val adapter = UserAdapter(UserListener {
                sleepId -> viewModel.onSleepNightClicked(sleepId)
        })



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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = GridLayoutManager(activity, 1)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(SimpleDividerItemDecoration(requireContext()))
        viewModel = ViewModelProvider(this, UsersViewModelFactory(mainRepository)).get(UsersViewModel::class.java)
        viewModel.usersList.observe(viewLifecycleOwner, {
            adapter.setUserList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(activity,"Error!", Toast.LENGTH_SHORT).show();
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
            }
        })

        viewModel.navigateToRandomUser.observe(viewLifecycleOwner, Observer {
                randomUser ->
            randomUser?.let {
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
                viewModel.onSleepDataQualityNavigated()
            }
        })


        viewModel.getAllUsers()

    }
}