package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.*
import com.saravanabalagi.dorset_mobile_app_2.db.AppDatabase
import com.saravanabalagi.dorset_mobile_app_2.models.User
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val scheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        scheduledExecutor.schedule({
            dbOps()
        }, 15, TimeUnit.SECONDS)
    }

    private fun dbOps() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-maps"
        ).build()

        val userDao = db.userDao()

        // Insert new user
//        userDao.insert(User(1, "Some name 1"))
//        userDao.insert(User(2, "Some name 2"))
//
//        val usersToInsert = listOf(
//            User(3, "Some name 3"),
//            User(4, "Some name 4"),
//            User(5, "Some name 5"),
//            User(6, "Some name 6"),
//        )
//        userDao.insertList(usersToInsert)

        // Update user
        val userToUpdate = User(3, "Updated name 3")
        userDao.update(userToUpdate)

        // Delete user
//        val userToDelete = User(2, null)
//        userDao.delete(userToDelete)
//
//        // retrieve all users
//        val users: List<User> = userDao.getAll()
//        users.map {
//            Log.i("DatabaseAct", it.toString())
//        }
    }
}

