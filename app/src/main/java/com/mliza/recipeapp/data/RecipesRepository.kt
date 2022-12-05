package com.mliza.recipeapp.data

import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.data.remote.RemoteDataSource

class RecipesRepository(
    private val remote: RemoteDataSource,
) {
    suspend fun getRecipes(): List<Recipe> {
        return try {remote.getRecipes() } catch (e: Exception) { throw e}
    }

    suspend fun getRecipeDetail(id: String): Recipe {
        return remote.getRecipeDetail(id)
    }
}
