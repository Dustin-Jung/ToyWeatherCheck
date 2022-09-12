package com.android.aop.part2.toyweathercheck.ui.home

import com.android.aop.part2.toyweathercheck.data.model.CityItem

sealed class HomeViewState {
    data class  RouteWeather(val item: CityItem) : HomeViewState()
    data class Error(val message: String) : HomeViewState()
}
