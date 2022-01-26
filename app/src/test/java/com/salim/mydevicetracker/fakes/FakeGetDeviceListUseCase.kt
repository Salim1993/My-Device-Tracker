package com.salim.mydevicetracker.fakes

import com.salim.mydevicetracker.deviceList.GetDeviceListUseCase
import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.networking.NetworkResult
import com.salim.mydevicetracker.networking.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeGetDeviceListUseCase: GetDeviceListUseCase {

    private val dataFlow = MutableStateFlow(listOf<Devices>())

    override fun getDeviceListFlow(): Flow<List<Devices>> = dataFlow

    override suspend fun getDeviceList(): NetworkResult {
        dataFlow.emit(getFakeData())

        return NetworkResult(NetworkStatus.SUCCESS, "")
    }

    private fun getFakeData(): List<Devices> {
        return listOf(
            Devices(
                id = "1234",
                type = "Sensor",
                price = 20,
                currency = "USD",
                isFavorite = false,
                imageUrl = "",
                title = "Test Sensor",
                description = "",
                isOnline = true
            )
        )
    }
}