package com.autught.chat.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.autught.chat.bean.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}