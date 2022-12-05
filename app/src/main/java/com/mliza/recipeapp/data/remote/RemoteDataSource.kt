package com.mliza.recipeapp.data.remote

import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.data.remote.retrofit.ServiceAPI

class RemoteDataSource(
    private val service: ServiceAPI
) {
    suspend fun getRecipes(): List<Recipe> {
        return service.getRecipes()
    }
    suspend fun getRecipeDetail(id: String): Recipe {
        return service.getRecipeDetail(id)
    }
}