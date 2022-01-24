package com.salim.mydevicetracker.devices


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "Devices")
data class Devices(
    val currency: String,
    val description: String,
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val price: Int,
    val title: String,
    val type: String
)