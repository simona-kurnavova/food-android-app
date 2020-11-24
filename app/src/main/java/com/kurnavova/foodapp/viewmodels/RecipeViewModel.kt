package com.kurnavova.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurnavova.foodapp.data.Recipe
import com.kurnavova.foodapp.utils.RecipeServiceHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel : ViewModel() {

    private val recipeServiceHandler by lazy { RecipeServiceHandler() }

    val recipe: MutableLiveData<Recipe> by lazy {
        MutableLiveData<Recipe>().apply {
            loadRecipe()
        }
    }

    var id: Int? = null

    fun getMyRecipe(): MutableLiveData<Recipe> = recipe

    private fun loadRecipe() {
        recipeServiceHandler.getRecipe(id ?: return).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                Log.d(TAG, "body: " + response.body().toString())
                recipe.value = response.body() as Recipe
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d(TAG, "error: " + t.message.toString())
            }
        })
    }
    companion object {
        const val TAG = "RecipeViewModel"
    }
}