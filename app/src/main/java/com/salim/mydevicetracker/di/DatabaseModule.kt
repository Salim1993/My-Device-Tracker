package com.salim.mydevicetracker.di

import android.content.Context
import androidx.room.Room
import com.salim.mydevicetracker.database.AppDatabase
import com.salim.mydevicetracker.database.AppDatabase.Companion.DATABASE_NAME
import com.salim.mydevicetracker.devices.DevicesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideDevicesDao(database: AppDatabase): DevicesDao {
        return database.devicesDao()
    }
}