package com.salim.mydevicetracker.devices


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Devices")
@Parcelize
data class Devices(
    val currency: String,
    val description: String,
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val price: Int,
    val title: String,
    val type: String,
    val isOnline: Boolean
) : Parcelable

fun Devices.contains(regex: String): Boolean {
    return title.contains(regex, true) || description.contains(regex, true)
            || type.contains(regex, true)
}