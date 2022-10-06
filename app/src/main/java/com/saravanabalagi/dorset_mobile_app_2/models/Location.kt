package com.saravanabalagi.dorset_mobile_app_2.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(val lid: Int, val lat: Double?, val long: Double?) {
    @PrimaryKey
    var id: Int = lid
    var latitide: Double? = lat
    var longitude: Double? = long

    override fun toString(): String {
        return "Location $id: $latitide $longitude"
    }
}