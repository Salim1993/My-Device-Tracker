package com.salim.mydevicetracker.deviceDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.salim.mydevicetracker.R
import com.salim.mydevicetracker.databinding.DeviceDetailsFragmentBinding
import timber.log.Timber

class DeviceDetailsFragment : Fragment(R.layout.device_details_fragment) {

    private val viewModel: DeviceDetailsViewModel by viewModels()
    private lateinit var binding: DeviceDetailsFragmentBinding
    private val args: DeviceDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DeviceDetailsFragmentBinding.bind(view)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            binding.costText.text = String.format(getString(R.string.price), it.price, it.currency)
            binding.typeText.text = String.format(getString(R.string.type), it.type)
            binding.isOnlineText.text = if(it.isOnline)
                getString(R.string.online_status)
            else
                getString(R.string.offline_status)
            binding.isFavoritedText.text = if(it.isFavorite)
                getString(R.string.is_favorite)
            else
                getString(R.string.not_favorite)

            binding.descriptionText.text = if(it.description.isEmpty())
                "NA"
            else
                it.description
        }

        viewModel.loadDeviceDetailsState(args.device)
    }
}