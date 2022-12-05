package com.mliza.recipeapp

import android.app.Application
import com.mliza.recipeapp.di.repositoryModule
import com.mliza.recipeapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelsModule))
        }

    }
}