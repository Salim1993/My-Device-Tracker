package com.salim.mydevicetracker.deviceList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.salim.mydevicetracker.R
import com.salim.mydevicetracker.databinding.DevicesListFragmentBinding
import com.salim.mydevicetracker.devices.Devices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevicesListFragment : Fragment(R.layout.devices_list_fragment) {

    private val viewModel: DevicesListViewModel by viewModels()
    private lateinit var binding: DevicesListFragmentBinding
    private lateinit var deviceListAdapter: DeviceListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupObservers()
    }

    private fun setupViews(view: View) {
        binding = DevicesListFragmentBinding.bind(view)

        deviceListAdapter = DeviceListAdapter(listOf(), this::onDeviceItemClickListener)
        with(binding.deviceList) {
            adapter = deviceListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setupObservers() {
        viewModel.deviceListFlow.asLiveData().observe(viewLifecycleOwner) {
            deviceListAdapter.newData(it)
        }
    }

    private fun onDeviceItemClickListener(device: Devices) {

    }
}