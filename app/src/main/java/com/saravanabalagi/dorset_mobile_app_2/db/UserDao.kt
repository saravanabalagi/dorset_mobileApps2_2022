package com.saravanabalagi.dorset_mobile_app_2.db

import androidx.room.*
import com.saravanabalagi.dorset_mobile_app_2.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Insert
    fun insertList(users: List<User>)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}