package com.saravanabalagi.dorset_mobile_app_2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saravanabalagi.dorset_mobile_app_2.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}