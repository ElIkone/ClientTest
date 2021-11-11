package com.android.clienttest.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.clienttest.databinding.ItemViewBinding
import com.android.clienttest.model.RandomUser
import com.android.clienttest.model.RandomUserList
import com.bumptech.glide.Glide

class UserAdapter(private val clickListener: UserListener) : RecyclerView.Adapter<MainViewHolder>() {
    var usersList = mutableListOf<RandomUser>()

    fun setUserList(userList: RandomUserList) {
        this.usersList = userList.results as MutableList<RandomUser>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val randomUser = usersList[position]

        holder.bind(randomUser, clickListener)

    }

    override fun getItemCount() = usersList.size


}

class MainViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind (item: RandomUser, clickListener: UserListener) {
        binding.randomUser = item
        binding.name.text= item.nat
        binding.gender.text = item.name.first
        Glide.with(binding.imageCharacter.context).load(item.picture.large).into(binding.imageCharacter)
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}


class UserListener(val clickListener: (randomUserId: String) -> Unit) {
    fun onClick(randomUser: RandomUser) = clickListener(randomUser.email)
}