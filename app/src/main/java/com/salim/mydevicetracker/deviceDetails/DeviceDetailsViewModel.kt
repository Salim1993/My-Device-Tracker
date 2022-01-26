package com.salim.mydevicetracker.deviceDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.mydevicetracker.devices.Devices
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class DeviceDetailsViewModel : ViewModel() {

    private val deviceDetailsStateFlow = MutableSharedFlow<Devices>()

    fun loadDeviceDetailsState(device: Devices): MutableSharedFlow<Devices> {
        viewModelScope.launch {
            deviceDetailsStateFlow.emit(device)
        }
        return deviceDetailsStateFlow
    }
}