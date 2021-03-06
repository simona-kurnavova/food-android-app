package com.kurnavova.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.network.RecipeDatabase
import com.kurnavova.foodapp.network.RecipeRepository
import com.kurnavova.foodapp.model.Recipe
import kotlinx.coroutines.Dispatchers

class RandomRecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    val recipes: LiveData<List<Recipe>> = loadRandomRecipe()

    private fun loadRandomRecipe() = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(recipeRepository.getRandomRecipes())
    }
}