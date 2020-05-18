package com.abelsuviri.data.repository

import com.abelsuviri.data.Result
import com.abelsuviri.data.model.MealsList
import com.abelsuviri.data.network.MealService

/**
 * @author Abel Suviri
 */

class MealsRepository constructor(private val mealService: MealService) {
    suspend fun getMeals(category: String): Result<MealsList> {
        return try {
            val response = mealService.getMeals(category)
            Result.Success(response)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}