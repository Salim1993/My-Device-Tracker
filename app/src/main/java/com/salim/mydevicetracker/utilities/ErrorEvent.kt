package com.salim.mydevicetracker.utilities

/**
 * Class to make sure event messages are only shown once. Will be consumed afterwards.
 */
class ErrorEvent(private val errorMessage: String) {

    private var isEventConsumed = false

    fun getErrorMessage(): String {
        return if(isEventConsumed) {
            ""
        } else {
            isEventConsumed = true
            errorMessage
        }
    }
}