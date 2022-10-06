package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.*

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        Thread {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-maps"
            ).build()

            val userDao = db.userDao()

            // Insert new user
            userDao.insert(User(1, "Some name 1"))
            userDao.insert(User(2, "Some name 2"))
            userDao.insert(User(3, "Some name 3"))
            userDao.insert(User(4, "Some name 4"))
            userDao.insert(User(5, "Some name 5"))
            userDao.insert(User(6, "Some name 6"))

            // Update user
            val userToUpdate = User(3, "Updated name 3")
            userDao.update(userToUpdate)

            // Delete user
            val userToDelete = User(2, null)
            userDao.delete(userToDelete)

            // retrieve all users
            val users: List<User> = userDao.getAll()
            users.map {
                Log.i("DatabaseAct", it.toString())
            }
        }.start()

    }
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

@Entity
data class User(val uid: Int, val uname: String?) {
    @PrimaryKey var id: Int = uid
    var name: String? = uname

    override fun toString(): String {
        return "$name ($id)"
    }
}

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