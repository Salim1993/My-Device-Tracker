package com.salim.mydevicetracker.deviceList

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
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
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        setupSearchView(menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupSearchView(menu: Menu) {
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                deviceListAdapter.filter.filter(p0)
                return false
            }
        })
    }

    private fun setupObservers() {
        viewModel.deviceListFlow.asLiveData().observe(viewLifecycleOwner) {
            deviceListAdapter.newData(it)
        }
    }

    private fun onDeviceItemClickListener(device: Devices) {
        val action = DevicesListFragmentDirections.actionNavHomeToDeviceDetailsFragment(device, device.title)
        findNavController().navigate(action)
    }
}