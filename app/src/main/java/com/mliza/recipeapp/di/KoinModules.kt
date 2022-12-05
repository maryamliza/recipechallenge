package com.mliza.recipeapp.di

import com.mliza.recipeapp.data.RecipesRepository
import com.mliza.recipeapp.data.remote.RemoteDataSource
import com.mliza.recipeapp.data.remote.retrofit.RetrofitManager
import com.mliza.recipeapp.data.remote.retrofit.ServiceAPI
import com.mliza.recipeapp.ui.detailScreen.DetailViewModel
import com.mliza.recipeapp.ui.homeScreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositoryModule = module {
    single { RecipesRepository(get()) }

    single { RemoteDataSource(get()) }
    single { RetrofitManager.getService() }
}

val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
