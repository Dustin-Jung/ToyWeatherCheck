package com.android.aop.part2.toyweathercheck.ui.weather

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.toyweathercheck.data.repo.WeatherRepository
import com.android.aop.part2.toyweathercheck.ext.ioScope
import com.android.aop.part2.toyweathercheck.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherViewStateLiveData = MutableLiveData<WeatherViewState>()
    val weatherViewStateLiveData: LiveData<WeatherViewState> = _weatherViewStateLiveData

    val weatherObservableField = ObservableField<String>()
    val humidityObservableField = ObservableField<String>()
    val celsiusObservableField = ObservableField<String>()


    fun routeCityList() {
        _weatherViewStateLiveData.value = WeatherViewState.RouteCityList
    }

    fun getWeather(id: Int?) {

        id?.let {
            ioScope {
                when (val response = weatherRepository.getWeatherList(id = id)) {

                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            weatherObservableField.set(response.data.weather[0].description)
                            humidityObservableField.set(response.data.main.humidity.toString())
                            celsiusObservableField.set(response.data.main.temp.toString())

                            val toWeatherIconItem = response.data.weather[0].copy(
                                icon = BASE_WEATHER_ICON + response.data.weather[0].icon + ".png"
                            )

                            _weatherViewStateLiveData.value =
                                WeatherViewState.GetWeatherIcon(toWeatherIconItem)

                        }
                    }

                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            _weatherViewStateLiveData.value = WeatherViewState.Error("날씨정보 불러오기 실패")
                        }
                    }
                }
            }
        }
    }

    companion object{
        private val BASE_WEATHER_ICON = "http://openweathermap.org/img/wn/"
    }
}
