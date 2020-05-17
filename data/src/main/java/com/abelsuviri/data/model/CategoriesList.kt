package com.abelsuviri.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Abel Suviri
 */

data class CategoriesList(
    @SerializedName("categories") val categories: List<MealCategory>
)