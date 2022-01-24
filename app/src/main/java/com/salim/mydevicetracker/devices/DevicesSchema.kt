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
    val type: String
) {
    companion object {
        fun convertToDevice(devicesSchema: DevicesSchema): Devices {
            return Devices(
                currency = devicesSchema.currency,
                description = devicesSchema.description,
                id = devicesSchema.id,
                imageUrl = devicesSchema.imageUrl,
                isFavorite = devicesSchema.isFavorite,
                price = devicesSchema.price,
                title = devicesSchema.title,
                type = devicesSchema.type
            )
        }
    }
}