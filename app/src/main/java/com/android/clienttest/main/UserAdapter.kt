package com.android.clienttest.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.clienttest.databinding.ItemViewBinding
import com.android.clienttest.model.RandomUser
import com.android.clienttest.model.RandomUserList
import com.bumptech.glide.Glide

class UserAdapter(private val listener: UserItemListener) : RecyclerView.Adapter<MainViewHolder>() {
    var usersList = mutableListOf<RandomUser>()

    fun setUserList(userList: RandomUserList) {
        this.usersList = userList.results as MutableList<RandomUser>
        notifyDataSetChanged()
    }

    interface UserItemListener {
        fun onClickedUser(userObject: RandomUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val randomUser = usersList[position]
        holder.binding.name.text= randomUser.nat
        holder.binding.gender.text = randomUser.name.first
        Glide.with(holder.itemView.context).load(randomUser.picture.large).into(holder.binding.imageCharacter)
        holder.bindItems(usersList[position])
    }

    override fun getItemCount() = usersList.size
}

class MainViewHolder(val binding: ItemViewBinding, private val listener: UserAdapter.UserItemListener) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    private lateinit var randomUser: RandomUser
    init {
        binding.root.setOnClickListener(this)
    }

    fun bindItems(item: RandomUser) {
        this.randomUser = item
    }
    override fun onClick(v: View?) {
        println(randomUser)
        listener.onClickedUser(randomUser)
    }
}