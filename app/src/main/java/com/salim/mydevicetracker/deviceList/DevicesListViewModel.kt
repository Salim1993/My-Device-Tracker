package com.salim.mydevicetracker.deviceList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.mydevicetracker.networking.NetworkStatus
import com.salim.mydevicetracker.utilities.ErrorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesListViewModel @Inject constructor(
    private val getDeviceListUseCase: GetDeviceListUseCase
) : ViewModel() {

    val deviceListFlow = getDeviceListUseCase.getDeviceListFlow()

    //var coroutineScope = coroutineScopeInject ?: viewModelScope

    private val _errorEvent = MutableStateFlow(ErrorEvent(""))
    val errorEvent = _errorEvent.asStateFlow()

    init {
        getDevicesList()
    }

    private fun getDevicesList() = viewModelScope.launch {
        val result = getDeviceListUseCase.getDeviceList()

        if(result.status == NetworkStatus.FAILURE) {
            _errorEvent.emit(ErrorEvent(result.message))
        }
    }
}