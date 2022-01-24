package com.salim.mydevicetracker.di

import com.salim.mydevicetracker.networking.DeviceApiService
import com.salim.mydevicetracker.networking.FakeApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    private const val POKEDEX_URL = "Random URL"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(POKEDEX_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /***
     * Actual api service that will be used once we device to remove the fake Api service.
     */
    @RealApiServiceQualifier
    @Provides
    fun getRealDeviceApiService(retrofit: Retrofit): DeviceApiService {
        return retrofit.create(DeviceApiService::class.java)
    }

    @FakeApiServiceQualifier
    @Provides
    fun getFakeDeviceApiService(): DeviceApiService {
        return FakeApiService()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealApiServiceQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeApiServiceQualifier