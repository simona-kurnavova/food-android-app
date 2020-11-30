package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.database.RecipeDatabase
import com.kurnavova.foodapp.database.RecipeRepository
import com.kurnavova.foodapp.model.Recipe
import kotlinx.coroutines.Dispatchers

class RecipeViewModel : ViewModel() {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    val id: MutableLiveData<String> = MutableLiveData()

    val recipe: LiveData<Recipe> = id.switchMap { id ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(recipeRepository.getRecipe(id))
        }
    }
}