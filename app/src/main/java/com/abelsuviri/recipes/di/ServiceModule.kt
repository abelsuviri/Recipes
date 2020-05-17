package com.abelsuviri.recipes.di

import com.abelsuviri.data.network.MealService
import com.abelsuviri.data.repository.CategoriesRepository
import com.abelsuviri.data.repository.MealsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Abel Suviri
 */

val serviceModule = module {
    single { CategoriesRepository(mealsService()) }
    single { MealsRepository(mealsService()) }
}

private fun mealsService(): MealService {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MealService::class.java)
}

private const val baseUrl = "https://www.themealdb.com"