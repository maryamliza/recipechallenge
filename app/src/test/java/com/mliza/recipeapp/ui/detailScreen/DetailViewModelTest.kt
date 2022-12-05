package com.mliza.recipeapp.ui.detailScreen

import com.mliza.recipeapp.data.RecipesRepository
import com.mliza.recipeapp.data.models.Recipe
import com.mliza.recipeapp.utils.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest : BaseTest() {

    private val recipesRepository = mockk<RecipesRepository>()
    lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(recipesRepository)
    }

    @Test
    fun `check if getRecipeDetail is fetched successfully`() {
//        GIVEN
        coEvery { recipesRepository.getRecipeDetail("12") } returns Recipe(name = "Ceviche")

//        WHEN
        viewModel.getRecipeDetail("12")

//        THEN
        coVerify { recipesRepository.getRecipeDetail("12") }
        Assert.assertEquals("Ceviche", viewModel.detailRecipe.value?.name)
    }

    @Test
    fun `check if getRecipeDetail is fetched with an error`() {
        coEvery { recipesRepository.getRecipeDetail("3") } throws Exception()

        viewModel.getRecipeDetail("3")

        coVerify { recipesRepository.getRecipeDetail("3") }
        Assert.assertEquals(null, viewModel.detailRecipe.value)
    }
}