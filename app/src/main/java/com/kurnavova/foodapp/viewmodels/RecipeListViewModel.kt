package com.kurnavova.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.database.RecipeDatabase
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.database.RecipeRepository
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import kotlinx.coroutines.Dispatchers

class RecipeListViewModel(application: Application) : BaseViewModel(application) {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    val filterQuery: MutableLiveData<RecipeFilterQuery> = MutableLiveData()

    val recipes: LiveData<List<Recipe>> = filterQuery.switchMap { filterQuery ->
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO + coroutineExceptionHandler) {
            emit(recipeRepository.searchRecipes(filterQuery))
        }
    }

    var filtersVisible: MutableLiveData<Boolean> = MutableLiveData()
}