package com.kurnavova.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurnavova.foodapp.data.Recipe
import com.kurnavova.foodapp.data.RecipeList
import com.kurnavova.foodapp.utils.RecipeServiceHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel : ViewModel() {

    private val recipeServiceHandler by lazy { RecipeServiceHandler() }

    private val recipes: MutableLiveData<List<Recipe>> by lazy {
        MutableLiveData<List<Recipe>>().apply {
            loadRecipes()
        }
    }

    init {
        loadRecipes()
    }

    fun getAllRecipes(): MutableLiveData<List<Recipe>> = recipes

    private fun loadRecipes() {
        recipeServiceHandler.getRecipes().enqueue(object : Callback<RecipeList> {
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                Log.d(TAG, "body: " + response.body().toString())
                recipes.value = response.body()?.recipes as MutableList<Recipe>
            }

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                Log.d(TAG, "error: " + t.message.toString())
            }
        })
    }
    companion object {
        const val TAG = "RecipeViewModel"
    }
}