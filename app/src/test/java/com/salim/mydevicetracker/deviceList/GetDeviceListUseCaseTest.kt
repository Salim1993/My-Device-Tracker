package com.salim.mydevicetracker.deviceList

import com.salim.mydevicetracker.deviceList.GetDeviceListUseCase.Companion.NO_HOST_CONNECTION_MESSAGE
import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.fakes.FakeDevicesDao
import com.salim.mydevicetracker.networking.DeviceApiService
import com.salim.mydevicetracker.networking.FakeApiService
import com.salim.mydevicetracker.networking.NetworkStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.net.UnknownHostException

class GetDeviceListUseCaseTest {

    @MockK lateinit var deviceApiService: DeviceApiService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Get device list, Success, Get new flow data and return network success result`() {
        //arrange
        val fakeService = FakeApiService() //for this test will use data returned from the fake, other will be mocked
        val dao = FakeDevicesDao()

        val testSubject = GetDeviceListUseCase(fakeService, dao)
        val resultFlow = testSubject.deviceListFlow

        //act
        val resultStatus = runBlocking {
            testSubject.getDeviceList()
        }

        //assert
        val resultDataFlow = runBlocking {
            resultFlow.first()[0]
        }
        val expectedDataFromFlow = getExpectedDevice()
        assertEquals(expectedDataFromFlow, resultDataFlow)
        assertEquals(NetworkStatus.SUCCESS, resultStatus.status)
    }

    private fun getExpectedDevice(): Devices {
        return Devices(
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
    }

    @Test
    fun `Get device list, Failure, Get failure network result`() {
        //arrange
        coEvery { deviceApiService.getListOfDevices() } throws UnknownHostException()
        val dao = FakeDevicesDao()

        val testSubject = GetDeviceListUseCase(deviceApiService, dao)

        //act
        val resultStatus = runBlocking {
            testSubject.getDeviceList()
        }

        //assert
        assertEquals(NetworkStatus.FAILURE, resultStatus.status)
        assertEquals(NO_HOST_CONNECTION_MESSAGE, resultStatus.message)
    }
}