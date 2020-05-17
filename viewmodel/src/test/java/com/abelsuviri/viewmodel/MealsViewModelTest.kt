package com.abelsuviri.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abelsuviri.data.Result
import com.abelsuviri.data.model.MealsList
import com.abelsuviri.data.repository.MealsRepository
import com.abelsuviri.viewmodel.mock.MealsJsonMock
import com.abelsuviri.viewmodel.rule.CoroutinesTestRule
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * @author Abel Suviri
 */

class MealsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var mealsRepository: MealsRepository

    private lateinit var mealsViewModel: MealsViewModel

    private lateinit var gson: Gson

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        mealsViewModel = MealsViewModel(mealsRepository)
        gson = Gson()
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun test_get_meals_successfully() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val mockResponse = Result.Success(gson.fromJson(MealsJsonMock.json, MealsList::class.java))

        Mockito.`when`(mealsRepository.getMeals("")).thenReturn(mockResponse)

        mealsViewModel.getMeals("")
        mealsViewModel.mealsListLiveData.observeForever {}

        Assert.assertEquals(mealsViewModel.mealsListLiveData.value, mockResponse.data)
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun test_get_categories_unsuccessfully() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val mockResponse = Result.Error(Throwable())

        Mockito.`when`(mealsRepository.getMeals("")).thenReturn(mockResponse)

        mealsViewModel.getMeals("")
        mealsViewModel.mealsListLiveData.observeForever {}

        Assert.assertEquals(mealsViewModel.mealsListLiveData.value, null)
    }
}