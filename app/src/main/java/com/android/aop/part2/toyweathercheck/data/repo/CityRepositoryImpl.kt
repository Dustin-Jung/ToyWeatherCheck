package com.android.aop.part2.toyweathercheck.data.repo

import com.android.aop.part2.toyweathercheck.data.model.CityList
import com.android.aop.part2.toyweathercheck.data.source.local.CityLocalDataSourceImpl
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity
import com.android.aop.part2.toyweathercheck.util.Result
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val cityLocalDataSourceImpl: CityLocalDataSourceImpl) :
    CityRepository {

    override suspend fun getCityList(): Result<CityList> =
        cityLocalDataSourceImpl.getCityList()

    override suspend fun registerCityList(cityList: CityList): Boolean =
        cityLocalDataSourceImpl.registerCityList(cityList)

    override suspend fun getAllCityEntity(): Result<List<CityEntity>> =
        cityLocalDataSourceImpl.getAllCityEntity()

    override suspend fun isExistCityEntityList(): Boolean =
        cityLocalDataSourceImpl.isExistCityEntityList()

}