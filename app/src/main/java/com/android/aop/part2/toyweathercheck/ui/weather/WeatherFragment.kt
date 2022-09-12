package com.android.aop.part2.toyweathercheck.ui.weather

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.aop.part2.toyweathercheck.R
import com.android.aop.part2.toyweathercheck.base.BaseFragment
import com.android.aop.part2.toyweathercheck.data.model.CityItem
import com.android.aop.part2.toyweathercheck.databinding.FragmentWeatherBinding
import com.android.aop.part2.toyweathercheck.ui.home.HomeViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>(R.layout.fragment_weather) {

    private val weatherViewModel by viewModels<WeatherViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private var clickItem : CityItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickItem = arguments?.getParcelable(CITY_ITEM)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()
    }

    private fun initUi(){

        binding.item = clickItem
    }

    private fun initViewModel(){

        weatherViewModel.getWeather(clickItem?.id)

        binding.viewModel = weatherViewModel

        weatherViewModel.weatherViewStateLiveData.observe(viewLifecycleOwner) { viewState ->

            when(viewState){

                is WeatherViewState.RouteCityList -> {
                    requireActivity().onBackPressed()
                }

                is WeatherViewState.GetWeatherIcon -> {
                    Glide.with(this)
                        .load(viewState.icon.icon)
                        .into(binding.weatherImageView)
                }
            }
        }
    }

    companion object{

        private const val CITY_ITEM = "city_item"

        fun  newInstance(cityItem: CityItem) : WeatherFragment{
            return WeatherFragment().apply {
                arguments =  bundleOf(Pair(CITY_ITEM,cityItem))
            }
        }
    }

}