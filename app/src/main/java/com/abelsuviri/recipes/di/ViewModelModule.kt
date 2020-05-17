package com.abelsuviri.recipes.di

import com.abelsuviri.viewmodel.CategoriesViewModel
import com.abelsuviri.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Abel Suviri
 */

val viewModelModule = module {
    viewModel { CategoriesViewModel(get()) }
    viewModel { MealsViewModel(get()) }
}