package com.example.mvvm_50.model

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

// создаем тип слушателя
typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()

    private val listeners = mutableListOf<UsersListener>()

    // Инициализируем список пользователей
    init {
        val faker = Faker.instance()
        IMAGES.shuffle() // генерируем список пользователей случайно
        users = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size] // чтобы изображения не повторялись
            ) }.toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users = ArrayList(users)
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    // Метод перемещения пользователя по списку как вверх так и вниз
    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex != -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex) // меняем элементы списка местами
        notifyChanges()
    }

    // метод для увольнения пользователя
    fun fireUser(user: User){
        val index = users.indexOfFirst { it.id == user.id }
        if (index == -1) return
        val updateUser = users[index].copy(company = "")
        users = ArrayList(users)
        users[index] = updateUser
        notifyChanges()
    }

    // метод для добавления слушателя
    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    // метод для удаления слушателя
    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    // метод для уведомления слушателей
    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://unsplash.com/photos/8xpbGo0YlT8",
            "https://unsplash.com/photos/_W2LsjgcQUQ",
            "https://unsplash.com/photos/815cLzGxbMQ",
            "https://unsplash.com/photos/Z394Octq0Es",
            "https://unsplash.com/photos/hVJeYTgfITU",
            "https://unsplash.com/photos/G8gLVJjgS9k",
            "https://unsplash.com/photos/sogMzsoVlYY",
            "https://unsplash.com/photos/JmjKb_8pK48",
            "https://unsplash.com/photos/Cn1U12cQ2L8",
            "https://unsplash.com/photos/cHf0rOR2ZYg"
        )
    }
}