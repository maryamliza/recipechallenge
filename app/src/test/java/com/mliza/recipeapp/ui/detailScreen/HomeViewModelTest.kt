package com.mliza.recipeapp.ui.detailScreen

import com.mliza.recipeapp.data.RecipesRepository
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.ui.homeScreen.HomeViewModel
import com.mliza.recipeapp.utils.BaseTest
import com.mliza.recipeapp.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeViewModelTest : BaseTest() {

    private val recipesRepository = mockk<RecipesRepository>()
    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(recipesRepository)
    }

    @Test
    fun `check if getRecipes is fetched successfully`() {
//        GIVEN
        coEvery { recipesRepository.getRecipes() } returns listOf(mockk(), mockk())

//        WHEN
        viewModel.getRecipes()

//        THEN
        coVerify { recipesRepository.getRecipes() }
        Assert.assertEquals(2, viewModel.listRecipesFiltered.getOrAwaitValue().size)
    }

    @Test
    fun `check if keyword is filtering results by name`() {
//        GIVEN
        coEvery { recipesRepository.getRecipes() } returns listOf(
            Recipe(name = "Ceviche", ingredients = "pescado"),
            Recipe(name = "Sushi", ingredients = "pescado")
        )

//        WHEN
        viewModel.getRecipes()
        viewModel.filter("ceviche")

//        THEN
        Assert.assertEquals(1, viewModel.listRecipesFiltered.getOrAwaitValue().size)
    }

    @Test
    fun `check if keyword is filtering results by ingredients`() {
//        GIVEN
        coEvery { recipesRepository.getRecipes() } returns listOf(
            Recipe(name = "Ceviche", ingredients = "pescado"),
            Recipe(name = "Sushi", ingredients = "pescado")
        )

//        WHEN
        viewModel.getRecipes()
        viewModel.filter("pescado")

//        THEN
        Assert.assertEquals(2, viewModel.listRecipesFiltered.getOrAwaitValue().size)
    }
}