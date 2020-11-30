package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kurnavova.foodapp.database.RecipeDatabase
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.database.RecipeRepository

class RecipeListViewModel : ViewModel() {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    val recipes: LiveData<List<Recipe>> = liveData {
        emit(recipeRepository.getRecipeList())
    }

}