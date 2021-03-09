package com.kurnavova.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kurnavova.foodapp.network.RecipeDatabase
import com.kurnavova.foodapp.network.RecipeRepository
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import kotlinx.coroutines.Dispatchers

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository by lazy { RecipeRepository(RecipeDatabase.provideRecipeDAO()) }

    private val filterQuery: MutableLiveData<RecipeFilterQuery> = MutableLiveData()

    var filtersVisible: MutableLiveData<Boolean> = MutableLiveData()

    var recipes = filterQuery.switchMap { filterQuery ->
        getRecipes(filterQuery)
    }

    fun loadRecipes(filterQuery: RecipeFilterQuery) {
        this.filterQuery.value = filterQuery
    }

    fun getFilterQuery(): RecipeFilterQuery? = filterQuery.value

    private fun getRecipes(filterQuery: RecipeFilterQuery) = liveData(context = viewModelScope.coroutineContext +
            Dispatchers.IO) {
        emit(recipeRepository.searchRecipes(filterQuery))
    }
}