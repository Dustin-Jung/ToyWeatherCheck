package com.android.aop.part2.toyweathercheck.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.toyweathercheck.data.repo.CityRepository
import com.android.aop.part2.toyweathercheck.ext.ioScope
import com.android.aop.part2.toyweathercheck.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {

    val inputSearchCityLiveData = MutableLiveData<String>()

    private val _cityViewStateLiveData = MutableLiveData<CityViewState>()
    val cityViewStateLiveData: LiveData<CityViewState> = _cityViewStateLiveData

    init {
        checkSaveCityList()
    }

    private fun checkSaveCityList(){
        ioScope {

            if(!isExistCityEntityList()){
                withContext(Dispatchers.Main){
                    _cityViewStateLiveData.value = CityViewState.ShowProgress
                }
                when(val result = cityRepository.getCityList()){

                    is Result.Success -> {
                        if(cityRepository.registerCityList(result.data)){
                            getCityList()
                        }else{
                            CityViewState.Error("에러발생")
                        }
                    }
                    is Result.Error -> {
                        CityViewState.Error("리스트 불러오기 에러발생")
                    }
                }

                withContext(Dispatchers.Main){
                    _cityViewStateLiveData.value = CityViewState.HideProgress
                }
            }else{
                getCityList()
            }
        }
    }

    private suspend fun isExistCityEntityList(): Boolean {
        return cityRepository.isExistCityEntityList()
    }

    private suspend fun getCityList() {

        when(val result = cityRepository.getAllCityEntity()){

            is Result.Success -> {
                withContext(Dispatchers.Main){
                    _cityViewStateLiveData.value = CityViewState.GetCityList(result.data.map{it.toCityItem()})
                }
            }

            is Result.Error -> {
                withContext(Dispatchers.Main){
                    _cityViewStateLiveData.value = CityViewState.ShowToast("에러발생")
                }
            }
        }
    }

    fun searchCity(){
        inputSearchCityLiveData.value?.let { searchString ->

            ioScope {
                when(val result = cityRepository.getAllCityEntity()){

                    is Result.Success -> {

                        val getCityList = result.data.map{ it.toCityItem() }

                        val filterSearchCityList = getCityList.filter{ it.name.contains(searchString)}

                        if(filterSearchCityList.isNotEmpty()){
                            withContext(Dispatchers.Main){
                                _cityViewStateLiveData.value =
                                    CityViewState.GetCityList(filterSearchCityList)
                            }
                        }
                    }

                    is Result.Error -> {
                        withContext(Dispatchers.Main){
                            _cityViewStateLiveData.value = CityViewState.ShowToast("에러발생")
                        }
                    }
                }
            }
        }
    }

    fun routeWeather(cityId: Int) {
        CityViewState.RouteWeather(cityId = cityId)
    }
}