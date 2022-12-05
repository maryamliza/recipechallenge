package com.mliza.recipeapp.data.remote.retrofit

import com.mliza.recipeapp.data.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceAPI {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipeDetail(@Path("id") id: String): Recipe
}