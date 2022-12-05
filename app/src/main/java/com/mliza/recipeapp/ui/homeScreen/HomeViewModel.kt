package com.mliza.recipeapp.ui.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mliza.recipeapp.data.RecipesRepository
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.utils.combineWith
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RecipesRepository
) : ViewModel() {

    private val listRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    private val keyword: MutableLiveData<String> = MutableLiveData()

    val listRecipesFiltered = listRecipes.combineWith(keyword) { list, key ->
        if (key == null) return@combineWith list.orEmpty()
        list?.filter {
            val isInName = it.name.contains(key, ignoreCase = true)
            val isInIngredients = it.ingredients.contains(key, ignoreCase = true)
            isInName || isInIngredients
        }.orEmpty()
    }

    fun filter(keyword: String) {
        this.keyword.value = keyword
    }

    fun getRecipes() = viewModelScope.launch {
        try {
            listRecipes.value = repository.getRecipes()
        } catch (e: Exception) {
            e.printStackTrace()
//            error.value = e.localizedMessage
        }
    }
}