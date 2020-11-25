package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeRepository

class RecipeViewModel : ViewModel() {

    private val recipeRepository by lazy { RecipeRepository() }
    private val recipe by lazy { loadRecipe() }

    var id: String? = null

    fun getMyRecipe() = recipe

    private fun loadRecipe(): MutableLiveData<Recipe>? {
        return recipeRepository.getRecipe(id ?: return null) as MutableLiveData<Recipe>
    }
}