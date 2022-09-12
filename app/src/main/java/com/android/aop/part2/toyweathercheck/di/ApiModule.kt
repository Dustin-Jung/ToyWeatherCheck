package com.android.aop.part2.toyweathercheck.di

import android.content.Context
import com.android.aop.part2.toyweathercheck.api.MetaWeatherApi
import com.android.aop.part2.toyweathercheck.api.WeatherIconApi
import com.android.aop.part2.toyweathercheck.util.City
import com.android.aop.part2.toyweathercheck.util.CityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideMetaWeatherApi(): MetaWeatherApi {
        return Retrofit.Builder()
            .baseUrl(METAWEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetaWeatherApi::class.java)
    }

    private const val METAWEATHER_URL = "https://api.openweathermap.org/"

    @Singleton
    @Provides
    fun provideCityListToJson(@ApplicationContext context: Context): City = CityImpl(context)

    @Singleton
    @Provides
    fun provideWeatherIcon(): WeatherIconApi {
        return Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherIconApi::class.java)
    }

    private const val WEATHER_URL = "https://api.openweathermap.org/"
}