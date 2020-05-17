package com.abelsuviri.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abelsuviri.data.Result
import com.abelsuviri.data.model.CategoriesList
import com.abelsuviri.data.repository.CategoriesRepository
import com.abelsuviri.viewmodel.mock.CategoriesJsonMock
import com.abelsuviri.viewmodel.rule.CoroutinesTestRule
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
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

class CategoriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var categoriesRepository: CategoriesRepository

    private lateinit var categoriesViewModel: CategoriesViewModel

    private lateinit var gson: Gson

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        categoriesViewModel = CategoriesViewModel(categoriesRepository)
        gson = Gson()
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun test_get_categories_successfully() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val mockResponse = Result.Success(gson.fromJson(CategoriesJsonMock.json, CategoriesList::class.java))

        Mockito.`when`(categoriesRepository.getCategories()).thenReturn(mockResponse)

        categoriesViewModel.getCategories()
        categoriesViewModel.categoriesListLiveData.observeForever {}

        Assert.assertEquals(categoriesViewModel.categoriesListLiveData.value, mockResponse.data)
    }

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    @Test
    fun test_get_categories_unsuccessfully() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val mockResponse = Result.Error(Throwable())

        Mockito.`when`(categoriesRepository.getCategories()).thenReturn(mockResponse)

        categoriesViewModel.getCategories()
        categoriesViewModel.categoriesListLiveData.observeForever {}

        Assert.assertEquals(categoriesViewModel.categoriesListLiveData.value, null)
    }
}