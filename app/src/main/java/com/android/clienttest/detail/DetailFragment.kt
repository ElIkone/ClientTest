package com.android.clienttest.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.clienttest.R
import com.bumptech.glide.Glide
import com.android.clienttest.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pictureSource = arguments?.getString("profilePicture")
        val name = arguments?.getString("name")
        val phone = arguments?.getString("phone")
        val nat = arguments?.getString("nat")
        val gender = arguments?.getString("gender")
        val email = arguments?.getString("email")

        binding.genderValue.text = gender
        binding.countryValue.text = nat
        binding.phoneValue.text = phone
        binding.userTitle.text = name
        binding.emailValue.text = email
        binding.genderValue.text = gender
        Glide.with(binding.image).load(pictureSource).into(binding.image)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }
}