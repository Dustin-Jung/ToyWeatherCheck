package com.android.aop.part2.toyweathercheck.util

import android.annotation.SuppressLint
import android.content.Context
import com.android.aop.part2.toyweathercheck.data.model.CityList
import com.google.gson.Gson

class CityImpl(private val context: Context) : City {
    override fun getCityList(): Result<CityList> =
        try {
            Result.Success(
                Gson().fromJson(
                    context.assets.open(CITY_LIST_JSON).bufferedReader().use { it.readLine() },
                    CityList::class.java
                )
            )
        } catch (e: Exception) {
            Result.Error(Throwable("Error Read Asset File"))
        }

    companion object {
        private const val CITY_LIST_JSON = "city.json"

        @SuppressLint("StaticFieldLeak")
        private var instace: CityImpl? = null

        fun getInstance(
            context: Context
        ): City =
            instace ?: CityImpl(context).also {
                instace = it
            }
    }
}

interface City {
    fun getCityList(): Result<CityList>
}
