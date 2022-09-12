package com.android.aop.part2.toyweathercheck.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.toyweathercheck.data.model.CityItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _homeViewStateLiveData = MutableLiveData<HomeViewState>()
    val homeViewStateLiveData: LiveData<HomeViewState> = _homeViewStateLiveData

    fun routeWeather(item: CityItem){
        _homeViewStateLiveData.value = HomeViewState.RouteWeather(item)
    }
}