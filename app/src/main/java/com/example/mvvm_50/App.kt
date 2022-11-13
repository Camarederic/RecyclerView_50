package com.example.mvvm_50

import android.app.Application
import com.example.mvvm_50.model.UsersService

class App:Application() {

    val usersService = UsersService()
}