package com.kurnavova.foodapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.network.RecipeDatabase
import com.kurnavova.foodapp.network.RecipeRepository
import com.kurnavova.foodapp.model.Recipe
import kotlinx.coroutines.Dispatchers

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    private var id: MutableLiveData<String> = MutableLiveData()

    val recipe: LiveData<Recipe?> = id.switchMap { id ->
        getRecipe(id)
    }

    fun loadRecipe(id: String?) {
        this.id.value = id
    }

    private fun getRecipe(id: String) = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(recipeRepository.getRecipe(id))
    }
}