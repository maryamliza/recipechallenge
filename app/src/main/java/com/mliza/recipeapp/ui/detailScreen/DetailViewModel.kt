package com.mliza.recipeapp.ui.detailScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mliza.recipeapp.data.RecipesRepository
import com.mliza.recipeapp.data.models.Recipe
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: RecipesRepository,
) : ViewModel() {
    val detailRecipe: MutableLiveData<Recipe> = MutableLiveData()

    fun getRecipeDetail(id: String) {
        viewModelScope.launch {
            detailRecipe.value = repository.getRecipeDetail(id)
        }
    }
}