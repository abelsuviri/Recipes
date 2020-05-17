package com.abelsuviri.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abelsuviri.data.Result
import com.abelsuviri.data.model.CategoriesList
import com.abelsuviri.data.repository.CategoriesRepository
import kotlinx.coroutines.launch

/**
 * @author Abel Suviri
 */

class CategoriesViewModel constructor(private val categoriesRepository: CategoriesRepository) : ViewModel() {
    val categoriesListLiveData: MutableLiveData<CategoriesList> = MutableLiveData()

    fun getCategories() {
        viewModelScope.launch {
            val response = categoriesRepository.getCategories()
            if (response is Result.Success) {
                categoriesListLiveData.postValue(response.data)
            } else if (response is Result.Error){
                Log.e("Error", response.exception.message.toString())
            }
        }
    }
}