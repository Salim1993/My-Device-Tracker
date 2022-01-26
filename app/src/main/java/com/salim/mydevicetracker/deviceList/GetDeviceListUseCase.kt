package com.salim.mydevicetracker.deviceList

import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.networking.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetDeviceListUseCase {

    fun getDeviceListFlow(): Flow<List<Devices>>

    suspend fun getDeviceList(): NetworkResult
}