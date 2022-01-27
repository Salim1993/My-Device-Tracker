package com.salim.mydevicetracker.deviceList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.salim.mydevicetracker.databinding.DevicesListItemBinding
import com.salim.mydevicetracker.devices.Devices
import com.salim.mydevicetracker.devices.contains

class DeviceListAdapter(
    private var unfilteredList: List<Devices>,
    private val clickListener: (Devices) -> Unit
) : RecyclerView.Adapter<DeviceListAdapter.DevicesListItemViewHolder>(), Filterable {

    var filteredList = unfilteredList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesListItemViewHolder {
        val binding = DevicesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DevicesListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DevicesListItemViewHolder, position: Int) {
        val device = filteredList[position]
        with(holder.binding) {
            nameText.text = device.title
            statusText.text = if(device.isOnline)
                "ONLINE"
            else
                "OFFLINE"

            root.setOnClickListener { clickListener(device) }
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun newData(newList: List<Devices>) {
        unfilteredList = newList
        submitNewList(newList)
    }

    private fun submitNewList(newList: List<Devices>) {
        val diff = DiffUtil.calculateDiff(DeviceListDiffUtilCallback(filteredList, newList))

        filteredList = newList
        diff.dispatchUpdatesTo(this)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Devices>()
                if(p0.isNullOrEmpty())
                    filteredList.addAll(unfilteredList)
                else {
                    val filterPattern = p0.toString().lowercase().trim()

                    val list = unfilteredList.filter {
                        it.contains(filterPattern)
                    }
                    filteredList.addAll(list)
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                val newList: List<Devices> = p1?.values as List<Devices>
                submitNewList(newList)
            }
        }
    }

    inner class DevicesListItemViewHolder(val binding: DevicesListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    class DeviceListDiffUtilCallback(private val oldList: List<Devices>, private val newList: List<Devices>)
        : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}