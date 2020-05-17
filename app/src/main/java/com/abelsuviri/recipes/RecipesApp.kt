package com.abelsuviri.recipes

import android.app.Application
import com.abelsuviri.recipes.di.serviceModule
import com.abelsuviri.recipes.di.viewModelModule
import org.koin.core.context.startKoin

/**
 * @author Abel Suviri
 */

class RecipesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(serviceModule, viewModelModule))
        }
    }
}