package com.example.lista20

object UserRepository {
    private val users = mutableListOf(
        User("user1", "password1"),
        User("user2", "password2"),
        User("user3", "password3"),
        User("user4", "password4"),
        User("user5", "password5")
    )

    fun getUsers(): List<User> = users

    fun addUser(user: User) {
        users.add(user)
    }

    fun authenticate(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }
}