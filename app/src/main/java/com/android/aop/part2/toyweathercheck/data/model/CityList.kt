package com.android.aop.part2.toyweathercheck.data.model

import android.os.Parcelable
import com.android.aop.part2.toyweathercheck.room.entity.CityEntity
import kotlinx.parcelize.Parcelize


class CityList : ArrayList<CityItem>()

@Parcelize
data class CityItem(
    val id: Int,
    val name: String,
    val country: String,
    val coord: Coord
): Parcelable {
    fun toCityEntity(): CityEntity =
        CityEntity(
            id = id,
            name = name,
            country = country,
            lat = coord.lat,
            lon = coord.lon
        )
}

@Parcelize
data class Coord(
    val lat: Double,
    val lon: Double
): Parcelable