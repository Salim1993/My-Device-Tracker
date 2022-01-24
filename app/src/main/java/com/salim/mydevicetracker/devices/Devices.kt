package com.salim.mydevicetracker.devices


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Devices(
    @Json(name = "Currency")
    val currency: String,
    @Json(name = "Description")
    val description: String,
    @Json(name = "Id")
    val id: String,
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "isFavorite")
    val isFavorite: Boolean,
    @Json(name = "Price")
    val price: Int,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Type")
    val type: String
)