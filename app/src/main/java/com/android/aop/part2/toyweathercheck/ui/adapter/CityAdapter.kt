package com.android.aop.part2.toyweathercheck.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.toyweathercheck.data.model.CityItem
import com.android.aop.part2.toyweathercheck.ui.adapter.viewholder.CityViewHolder

class CityAdapter : RecyclerView.Adapter<CityViewHolder>() {

    private lateinit var itemClickListener: (item: CityItem) -> Unit
    private val cityItemList = mutableListOf<CityItem>()

    fun setOnItemClickListener(listener: (item: CityItem) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(parent, itemClickListener)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityItemList[position])
    }

    override fun getItemCount(): Int =
        cityItemList.size

    fun addAll(list: List<CityItem>) {
        cityItemList.clear()
        cityItemList.addAll(list)
        notifyDataSetChanged()
    }
}