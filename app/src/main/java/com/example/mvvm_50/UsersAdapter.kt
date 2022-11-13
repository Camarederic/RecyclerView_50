package com.example.mvvm_50

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_50.databinding.ItemUserBinding
import com.example.mvvm_50.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class UserViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.textViewUserName.text = user.name
        holder.binding.textViewCompany.text = user.company
        if (user.photo.isNotBlank()) {
            Glide.with(holder.binding.imageViewUser.context)
                .load(user.photo)
                .circleCrop()
                .placeholder(R.drawable.ic_user_avatar)
                .error(R.drawable.ic_user_avatar)
                .into(holder.binding.imageViewUser)
        } else {
            holder.binding.imageViewUser.setImageResource(R.drawable.ic_user_avatar)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }
}