package com.abelsuviri.data.network

import com.abelsuviri.data.model.CategoriesList
import com.abelsuviri.data.model.MealsList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Abel Suviri
 */

interface MealService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): CategoriesList

    @GET("api/json/v1/1/filter.php")
    suspend fun getMeals(
        @Query("c") category: String
    ): MealsList
}