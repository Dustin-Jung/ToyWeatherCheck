package com.android.aop.part2.toyweathercheck.ui.city

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.aop.part2.toyweathercheck.R
import com.android.aop.part2.toyweathercheck.base.BaseFragment
import com.android.aop.part2.toyweathercheck.databinding.FragmentCityBinding
import com.android.aop.part2.toyweathercheck.ui.adapter.CityAdapter
import com.android.aop.part2.toyweathercheck.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment :  BaseFragment<FragmentCityBinding>(R.layout.fragment_city) {

    private val cityViewModel by viewModels<CityViewModel>()

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private val cityAdapter = CityAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initViewModel()
    }

    private fun initUi(){

        with(binding){

            recyclerView.adapter = cityAdapter

            cityAdapter.setOnItemClickListener { cityItem ->
                homeViewModel.routeWeather(cityItem)
            }
        }
    }

    private fun initViewModel(){
        binding.viewModel = cityViewModel

        cityViewModel.cityViewStateLiveData.observe(viewLifecycleOwner){ viewState ->

            when(viewState){

                is CityViewState.GetCityList ->{
                    cityAdapter.addAll(viewState.list)
                }

                is CityViewState.ShowProgress ->{
                    binding.progress.bringToFront()
                    binding.progress.isVisible = true
                }

                is CityViewState.HideProgress -> {
                    binding.progress.isVisible = false
                }

                is CityViewState.ShowToast -> {
                    Toast.makeText(requireContext(),viewState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}