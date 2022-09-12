package com.android.aop.part2.toyweathercheck.ui.city

import com.android.aop.part2.toyweathercheck.data.model.CityItem

sealed class CityViewState {

    data class GetCityList(val list: List<CityItem>) : CityViewState()
    data class RouteWeather(val cityId: Int) : CityViewState()
    data class Error(val errorMessage: String) : CityViewState()
    data class ShowToast(val message: String) : CityViewState()
    object ShowProgress : CityViewState()
    object HideProgress : CityViewState()
}
