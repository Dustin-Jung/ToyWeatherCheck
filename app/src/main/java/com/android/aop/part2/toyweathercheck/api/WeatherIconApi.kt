package com.android.aop.part2.toyweathercheck.api

import com.android.aop.part2.toyweathercheck.api.response.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherIconApi {

    companion object {
        private const val REST_API_KEY = "d92ba4f5d533536365b0797ec4de5696"
        private const val WEATERSTATUS = "img/wn/10d@2x.png"
    }

    @GET(WEATERSTATUS)
    suspend fun weatherStatus(
        @Query("icon") icon: String
    ): Weather
}