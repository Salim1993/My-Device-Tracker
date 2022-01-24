package com.salim.mydevicetracker.networking

import com.salim.mydevicetracker.devices.DevicesSchema
import retrofit2.http.GET

interface DeviceApiService {

    @GET("device")
    suspend fun getListOfDevices(): List<DevicesSchema>
}