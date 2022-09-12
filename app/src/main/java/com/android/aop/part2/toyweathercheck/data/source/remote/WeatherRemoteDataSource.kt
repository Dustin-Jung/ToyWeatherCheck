package com.android.aop.part2.toyweathercheck.data.source.remote


import com.android.aop.part2.toyweathercheck.api.response.Weather
import com.android.aop.part2.toyweathercheck.api.response.WeatherResponse
import com.android.aop.part2.toyweathercheck.util.Result

interface WeatherRemoteDataSource {

    suspend fun getWeatherList(id: Int): Result<WeatherResponse>

    suspend fun getWeatherIcon(icon: String): Result<Weather>


}