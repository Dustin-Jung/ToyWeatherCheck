package com.android.aop.part2.toyweathercheck.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.toyweathercheck.R
import com.android.aop.part2.toyweathercheck.data.model.CityItem

class CityViewHolder (parent: ViewGroup, private val itemClickListener: (item: CityItem) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
    ) {
    private val cityName: TextView = itemView.findViewById(R.id.item_city_name)

    fun bind(cityItem: CityItem) {

        itemView.setOnClickListener {
            itemClickListener(cityItem)
        }

        cityName.text = cityItem.name
    }
}