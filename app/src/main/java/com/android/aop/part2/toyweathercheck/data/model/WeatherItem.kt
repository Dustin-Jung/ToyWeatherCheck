package com.android.aop.part2.toyweathercheck.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherItem(
    val name: String,
    val country: String,
    val weather: String,
    val humidity: String,
    val celsius: String
) : Parcelable
