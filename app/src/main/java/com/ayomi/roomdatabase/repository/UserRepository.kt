package com.ayomi.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.ayomi.roomdatabase.data.User
import com.ayomi.roomdatabase.data.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUSer(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUsers()
    }
}