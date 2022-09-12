package com.android.aop.part2.toyweathercheck.data.source.local

import com.android.aop.part2.toyweathercheck.data.model.CityList
import com.android.aop.part2.toyweathercheck.room.dao.CityDao
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity
import com.android.aop.part2.toyweathercheck.util.City
import com.android.aop.part2.toyweathercheck.util.Result
import java.lang.Exception
import javax.inject.Inject

class CityLocalDataSourceImpl @Inject constructor(
    private val cityDao: CityDao,
    private val city: City
) : CityLocalDataSource {

    override suspend fun getCityList(): Result<CityList> =
        city.getCityList()

    override suspend fun registerCityList(cityList: CityList): Boolean =
        registerAll(cityList)

    override suspend fun getAllCityEntity(): Result<List<CityEntity>> {
        return  try {
            val result = cityDao.getAll()
            Result.Success(result)

        }catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun isExistCityEntityList(): Boolean =
        cityDao.getAll().isNotEmpty()

    private fun registerAll(cityList: CityList) : Boolean{
        var result = true
        cityList.forEach{
            result = result.and(cityDao.registerCityEntity(it.toCityEntity())>0)
        }
        return result
    }

}