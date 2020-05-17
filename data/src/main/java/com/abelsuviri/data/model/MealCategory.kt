package com.abelsuviri.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Abel Suviri
 */

data class MealCategory(
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val thumbnail: String
)