package com.android.aop.part2.toyweathercheck.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerCityEntity(cityEntity: CityEntity): Long

    @Query("SELECT * FROM city_table")
    fun getAll(): List<CityEntity>

    @Query("SELECT * FROM city_table WHERE name = (:cityName)")
    fun getCityEntity(cityName: String): CityEntity
}