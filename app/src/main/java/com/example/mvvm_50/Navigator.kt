package com.example.mvvm_50

import com.example.mvvm_50.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)
}