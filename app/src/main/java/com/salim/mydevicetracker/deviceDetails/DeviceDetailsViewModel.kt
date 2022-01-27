package com.salim.mydevicetracker.deviceDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.mydevicetracker.devices.Devices
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DeviceDetailsViewModel : ViewModel() {

    //Had to use Live data instead of shared flow, since got into weird issue where
    // flow wouldn't emit new data
    private val _liveData = MutableLiveData<Devices>()
    val liveData: LiveData<Devices>
        get() = _liveData

    fun loadDeviceDetailsState(device: Devices) {
        _liveData.postValue(device)
    }
}