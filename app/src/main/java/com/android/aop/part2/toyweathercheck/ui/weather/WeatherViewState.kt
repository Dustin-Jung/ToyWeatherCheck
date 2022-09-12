package com.android.aop.part2.toyweathercheck.ui.weather

import com.android.aop.part2.toyweathercheck.api.response.Weather
import com.android.aop.part2.toyweathercheck.data.model.WeatherItem

sealed class WeatherViewState {

    object RouteCityList : WeatherViewState()
    data class GetWeather(val weatherItem: WeatherItem) : WeatherViewState()
    data class GetWeatherIcon(val icon: Weather) : WeatherViewState()
    data class Error(val message: String) : WeatherViewState()

}
