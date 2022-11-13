package com.example.mvvm_50

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_50.databinding.ActivityMainBinding
import com.example.mvvm_50.model.UsersListener
import com.example.mvvm_50.model.UsersService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter

    // получаем доступ к классу UsersService
    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = usersAdapter

        usersService.addListener(usersListener)
    }

    // чтобы избежать утечек памяти удаляем слушатель
    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    // добавляем слушателя, который будет прослушивать изменения, которые происходят в классе UserService
    private val usersListener: UsersListener = {
        usersAdapter.users = it
    }
}