package com.salim.mydevicetracker.deviceDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.salim.mydevicetracker.R
import com.salim.mydevicetracker.databinding.DeviceDetailsFragmentBinding

class DeviceDetailsFragment : Fragment(R.layout.device_details_fragment) {

    private val viewModel: DeviceDetailsViewModel by viewModels()
    private lateinit var binding: DeviceDetailsFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DeviceDetailsFragmentBinding.bind(view)
        setupObservers()
    }

    private fun setupObservers() {
        //viewModel.loadDeviceDetailsState()
    }
}