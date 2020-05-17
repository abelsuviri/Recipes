package com.abelsuviri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abelsuviri.data.Result
import com.abelsuviri.data.model.MealsList
import com.abelsuviri.data.repository.MealsRepository
import kotlinx.coroutines.launch

/**
 * @author Abel Suviri
 */

class MealsViewModel constructor(private val mealsRepository: MealsRepository) : ViewModel() {
    val mealsListLiveData: MutableLiveData<MealsList> = MutableLiveData()

    fun getMeals(category: String) {
        viewModelScope.launch {
            val response = mealsRepository.getMeals(category)
            if (response is Result.Success) {
                mealsListLiveData.postValue(response.data)
            } else if (response is Result.Error) {
                Log.e("Error", response.exception.message.toString())
            }
        }
    }
}