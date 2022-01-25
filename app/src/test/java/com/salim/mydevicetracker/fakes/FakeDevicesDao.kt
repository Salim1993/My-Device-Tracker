package com.salim.mydevicetracker.fakes

import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.devices.DevicesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDevicesDao: DevicesDao {

    private val flow = MutableStateFlow(listOf<Devices>())

    override suspend fun insertAllDevices(list: List<Devices>) {
        flow.emit(list)
    }

    override fun getAllDevices(): Flow<List<Devices>> {
        return flow
    }
}