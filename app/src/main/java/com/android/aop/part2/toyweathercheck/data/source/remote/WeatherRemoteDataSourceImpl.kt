package com.android.aop.part2.toyweathercheck.data.source.remote

import com.android.aop.part2.toyweathercheck.api.MetaWeatherApi
import com.android.aop.part2.toyweathercheck.api.WeatherIconApi
import com.android.aop.part2.toyweathercheck.api.response.Weather
import com.android.aop.part2.toyweathercheck.api.response.WeatherResponse
import com.android.aop.part2.toyweathercheck.util.Result
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val metaWeatherApi: MetaWeatherApi,
    private val weatherIconApi: WeatherIconApi
) :
    WeatherRemoteDataSource {
    override suspend fun getWeatherList(id: Int): Result<WeatherResponse> {
        return try {
            val result = metaWeatherApi.search(id = id)
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(Throwable())
        }
    }

    override suspend fun getWeatherIcon(icon: String): Result<Weather> {
        return try {
            val result = weatherIconApi.weatherStatus(icon = icon)
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(Throwable())
        }
    }
}