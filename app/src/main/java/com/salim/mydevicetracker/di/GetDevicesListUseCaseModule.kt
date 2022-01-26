package com.salim.mydevicetracker.di

import android.content.Context
import androidx.room.Room
import com.salim.mydevicetracker.database.AppDatabase
import com.salim.mydevicetracker.database.AppDatabase.Companion.DATABASE_NAME
import com.salim.mydevicetracker.deviceList.GetDeviceListUseCase
import com.salim.mydevicetracker.deviceList.GetDeviceListUseCaseImpl
import com.salim.mydevicetracker.devices.DevicesDao
import com.salim.mydevicetracker.networking.DeviceApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GetDevicesListUseCaseModule {

    @Provides
    fun provideGetDevicesListUseCase(
        @FakeApiServiceQualifier service: DeviceApiService,
        dao: DevicesDao
    ): GetDeviceListUseCase {
        return GetDeviceListUseCaseImpl(service, dao)
    }
}