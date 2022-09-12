package com.android.aop.part2.toyweathercheck.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.aop.part2.toyweathercheck.room.dao.CityDao
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}