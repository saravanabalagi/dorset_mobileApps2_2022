package com.saravanabalagi.dorset_mobile_app_2.models

import androidx.room.Entity
import androidx.room.Relation

@Entity
class UserWithLocation {
    lateinit var user: User
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    lateinit var locations: List<Location>
}