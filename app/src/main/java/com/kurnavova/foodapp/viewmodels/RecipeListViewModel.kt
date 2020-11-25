package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeRepository

class RecipeListViewModel : ViewModel() {

    private val recipeRepository by lazy { RecipeRepository() }
    private val recipes by lazy { loadRecipes() }

    fun getAllRecipes() = recipes

    private fun loadRecipes() = recipeRepository.getRecipeList() as MutableLiveData<List<Recipe>>
}