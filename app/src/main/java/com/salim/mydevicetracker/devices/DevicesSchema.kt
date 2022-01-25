package com.salim.mydevicetracker.devices


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/***
 * Schema to map data from API to internal model. @see [Devices]
 */
@JsonClass(generateAdapter = true)
data class DevicesSchema(
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
    val type: String,
    @Json(name = "IsOnline")
    val isOnline: Boolean
)

fun DevicesSchema.convertToDevice(): Devices {
    return Devices(
        currency = currency,
        description = description,
        id = id,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        price = price,
        title = title,
        type = type,
        isOnline = isOnline
    )
}