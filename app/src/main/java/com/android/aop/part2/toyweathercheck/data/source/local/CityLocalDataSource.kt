package com.android.aop.part2.toyweathercheck.data.source.local

import com.android.aop.part2.toyweathercheck.data.model.CityList
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity
import com.android.aop.part2.toyweathercheck.util.Result

interface CityLocalDataSource {

    suspend fun getCityList(): Result<CityList>

    suspend fun registerCityList(cityList: CityList): Boolean

    suspend fun getAllCityEntity(): Result<List<CityEntity>>

    suspend fun isExistCityEntityList(): Boolean
}