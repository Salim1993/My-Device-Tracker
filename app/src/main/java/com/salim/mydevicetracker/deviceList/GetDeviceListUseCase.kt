package com.salim.mydevicetracker.deviceList

import com.salim.mydevicetracker.devices.DevicesDao
import com.salim.mydevicetracker.devices.convertToDevice
import com.salim.mydevicetracker.di.FakeApiServiceQualifier
import com.salim.mydevicetracker.networking.DeviceApiService
import com.salim.mydevicetracker.networking.NetworkResult
import com.salim.mydevicetracker.networking.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class GetDeviceListUseCase @Inject constructor(
    @FakeApiServiceQualifier private val service: DeviceApiService,
    private val dao: DevicesDao
) {

    val deviceListFlow = dao.getAllDevices()

    suspend fun getDeviceList(): NetworkResult {
        try {
            withContext(Dispatchers.IO) {
                val listSchema = service.getListOfDevices()
                val listOfDevices = listSchema.map { it.convertToDevice() }
                dao.insertAllDevices(listOfDevices)
            }
            return NetworkResult(NetworkStatus.SUCCESS, "")
        } catch (e: HttpException) {
            e.printStackTrace()
            return NetworkResult(NetworkStatus.FAILURE, e.message())
        } catch (e: UnknownHostException) {
            //cant connect to host
            Timber.e("Got UnknownHostException. Could not connect to host.")
            return NetworkResult(NetworkStatus.FAILURE, "Could not connect to host.")
        }
    }
}