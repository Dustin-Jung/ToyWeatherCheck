package com.android.aop.part2.toyweathercheck.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.android.aop.part2.toyweathercheck.R
import com.android.aop.part2.toyweathercheck.base.BaseActivity
import com.android.aop.part2.toyweathercheck.databinding.ActivityHomeBinding
import com.android.aop.part2.toyweathercheck.ui.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel(){

        homeViewModel.homeViewStateLiveData.observe(this){ viewState ->

            when(viewState){

                is HomeViewState.RouteWeather ->{
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, WeatherFragment.newInstance(viewState.item))
                        .addToBackStack(null).commit()
                }

                is HomeViewState.Error -> {
                    Toast.makeText(this, viewState.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}