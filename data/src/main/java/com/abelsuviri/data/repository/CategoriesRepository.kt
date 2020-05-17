package com.abelsuviri.data.repository

import com.abelsuviri.data.Result
import com.abelsuviri.data.model.CategoriesList
import com.abelsuviri.data.network.MealService

/**
 * @author Abel Suviri
 */

class CategoriesRepository constructor(private val mealService: MealService) {
    suspend fun getCategories(): Result<CategoriesList> {
        return try {
            val response = mealService.getCategories().await()
            Result.Success(response)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}