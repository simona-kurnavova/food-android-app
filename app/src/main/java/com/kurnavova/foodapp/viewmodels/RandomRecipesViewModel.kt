package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.database.RecipeDatabase
import com.kurnavova.foodapp.database.RecipeRepository
import com.kurnavova.foodapp.model.Recipe
import kotlinx.coroutines.Dispatchers

class RandomRecipesViewModel : BaseViewModel() {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    val recipes: LiveData<List<Recipe>> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO +
            coroutineExceptionHanlder) { emit(recipeRepository.getRandomRecipes()) }
}