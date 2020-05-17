package com.abelsuviri.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Abel Suviri
 */

data class MealsList(
    @SerializedName("meals") val meals: List<Meal>
)