package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DatabaseActivity : AppCompatActivity() {
    val TAG = "DatabaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")

        // Create a new user with a first and last name
        val user1 = User("Ada1", "Lovelace", 1915)
        val user2 = User("Ada2", "Lovelace", 1915)
        val user3 = User("Ada3", "Lovelace", 1915)
        val user4 = User("Ada4", "Lovelace", 1915)
        val user5 = User("Ada5", "Lovelace", 1915)

        arrayOf(user1, user2, user3, user4, user5).map { user ->
            val name = "${user.fname} ${user.lname}"
            usersCollection.add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "User $name added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding user $name", e)
                }
        }
    }
}

class User(
    var fname: String,
    var lname: String,
    var born: Int,
)

