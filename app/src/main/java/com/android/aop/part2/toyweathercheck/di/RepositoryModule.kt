package com.android.aop.part2.toyweathercheck.di

import com.android.aop.part2.toyweathercheck.data.repo.CityRepository
import com.android.aop.part2.toyweathercheck.data.repo.CityRepositoryImpl
import com.android.aop.part2.toyweathercheck.data.repo.WeatherRepository
import com.android.aop.part2.toyweathercheck.data.repo.WeatherRepositoryImpl
import com.android.aop.part2.toyweathercheck.data.source.local.CityLocalDataSource
import com.android.aop.part2.toyweathercheck.data.source.local.CityLocalDataSourceImpl
import com.android.aop.part2.toyweathercheck.data.source.remote.WeatherRemoteDataSource
import com.android.aop.part2.toyweathercheck.data.source.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Singleton
    @Binds
    abstract fun bindWeatherRemoteDataSource(
        weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindCityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ) : CityRepository

    @Singleton
    @Binds
    abstract fun bindCityLocalDataSource(
        cityLocalDataSourceImpl: CityLocalDataSourceImpl
    ) : CityLocalDataSource
}