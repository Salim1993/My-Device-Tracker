package com.salim.mydevicetracker.devices

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DevicesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDevices(list: List<Devices>)

    @Query("SELECT * FROM Devices")
    fun getAllDevices(): List<Devices>
}