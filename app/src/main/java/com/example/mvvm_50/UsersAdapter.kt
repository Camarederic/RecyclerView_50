package com.example.mvvm_50

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_50.databinding.ItemUserBinding
import com.example.mvvm_50.model.User

interface UserActionListener {

    fun onUserMove(user: User, moveBy: Int)

    fun onUserDelete(user: User)

    fun onUserDetails(user: User)
}

class UsersAdapter(
    private val actionListener: UserActionListener
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(), View.OnClickListener {

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

        binding.root.setOnClickListener(this)
        binding.imageViewMore.setOnClickListener(this)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.tag = user
        holder.binding.imageViewMore.tag = user
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

    override fun onClick(v: View) {
        val user = v.tag as User
        when (v.id) {
            R.id.imageViewMore -> {
                // todo
            }else -> {
                actionListener.onUserDetails(user)
            }
        }
    }
}