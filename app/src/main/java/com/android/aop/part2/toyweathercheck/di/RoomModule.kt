package com.android.aop.part2.toyweathercheck.di

import android.content.Context
import androidx.room.Room
import com.android.aop.part2.toyweathercheck.room.dao.CityDao
import com.android.aop.part2.toyweathercheck.room.database.CityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideCityDao(cityDatabase: CityDatabase): CityDao {
        return cityDatabase.cityDao()
    }

    @Singleton
    @Provides
    fun provideCriminal(@ApplicationContext appContext: Context): CityDatabase {
        return Room.databaseBuilder(
            appContext,
            CityDatabase::class.java,
            "criminal_table"
        ).fallbackToDestructiveMigration()
            .build()
    }
}