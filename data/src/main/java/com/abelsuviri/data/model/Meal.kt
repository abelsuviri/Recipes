package com.abelsuviri.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Abel Suviri
 */

data class Meal(
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnail: String
)