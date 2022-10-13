package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DatabaseActivity : AppCompatActivity() {
    val TAG = "DatabaseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val db = Firebase.firestore
        val usersCollection = db.collection("users")

        // Create a new user with a first and last name
//        val user1 = User(1,"Ada1", "Lovelace", 1915)
//        val user2 = User(2,"Ada2", "Lovelace", 1915)
//        val user3 = User(3,"Ada3", "Lovelace", 1915)
//        val user4 = User(4,"Ada4", "Lovelace", 1915)
//        val user5 = User(5,"Ada5", "Lovelace", 1915)
//
//        arrayOf(user1, user2, user3, user4, user5).map { user ->
//            val name = "${user.fname} ${user.lname}"
//            usersCollection.document(user.id.toString())
//                .set(user)
//                .addOnSuccessListener {
//                    Log.d(TAG, "User $name added with ID: ${user.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding user $name", e)
//                }
//        }

        // Update and delete ops
//        usersCollection.document("1").update("last", "Updated lastname")
//        usersCollection.document("5").delete()
//            .addOnSuccessListener {
//                Log.i(TAG, "Delete user 5 succeeded")
//            }
//            .addOnFailureListener {
//            Log.i(TAG, "Delete user 5 failed")
//        }

        // Get
        val userId = 3
        usersCollection.document(userId.toString()).get().addOnSuccessListener {
            val user = it.toObject<User>()
            if (user != null)
                Log.i(TAG, user.fname)
            else Log.e(TAG, "User $userId is null")
        }
    }
}

class User(
    var id: Int = -1,
    var fname: String = "",
    var lname: String = "",
    var born: Int = -1,
)

