package com.salim.mydevicetracker.networking

data class NetworkResult(val status: NetworkStatus, val message: String)

enum class NetworkStatus { SUCCESS, FAILURE }