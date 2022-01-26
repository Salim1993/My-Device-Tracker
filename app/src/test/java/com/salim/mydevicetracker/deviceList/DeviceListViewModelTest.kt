package com.salim.mydevicetracker.deviceList

import com.salim.mydevicetracker.deviceList.GetDeviceListUseCaseImpl.Companion.NO_HOST_CONNECTION_MESSAGE
import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.fakes.FakeGetDeviceListUseCase
import com.salim.mydevicetracker.networking.NetworkResult
import com.salim.mydevicetracker.networking.NetworkStatus
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class DeviceListViewModelTest {

    @MockK(relaxed = true) lateinit var deviceListUseCase: GetDeviceListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun clean() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get devices list, Success, Device list in data flow`() = runTest {
        //Arrange
        val fakeUseCase = FakeGetDeviceListUseCase()

        //Act (Since this calls method were testing on init)
        val testSubject = DevicesListViewModel(fakeUseCase)

        //Assert
        val result = testSubject.deviceListFlow.drop(1).first()[0] //first is empty list

        val expected = Devices(
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
        assertEquals(expected, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get devices list, Failure, Error event fired`() = runTest {
        //Arrange
        val networkFailure = NetworkResult(NetworkStatus.FAILURE, NO_HOST_CONNECTION_MESSAGE)
        coEvery { deviceListUseCase.getDeviceList() } returns networkFailure

        //Act (Since this calls method were testing on init)
        val testSubject = DevicesListViewModel(deviceListUseCase)

        //Assert
        val result = testSubject.errorEvent.drop(1).first().getErrorMessage()

        val expected = NO_HOST_CONNECTION_MESSAGE
        assertEquals(expected, result)
    }
}