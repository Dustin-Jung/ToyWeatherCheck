package com.android.aop.part2.toyweathercheck.data.repo

import com.android.aop.part2.toyweathercheck.api.response.Weather
import com.android.aop.part2.toyweathercheck.api.response.WeatherResponse
import com.android.aop.part2.toyweathercheck.data.source.remote.WeatherRemoteDataSource
import com.android.aop.part2.toyweathercheck.util.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherRemoteDataSource: WeatherRemoteDataSource) :
    WeatherRepository {
    override suspend fun getWeatherList(id: Int): Result<WeatherResponse> =
        weatherRemoteDataSource.getWeatherList(id)

    override suspend fun getWeatherIcon(icon: String): Result<Weather> =
        weatherRemoteDataSource.getWeatherIcon(icon)
}