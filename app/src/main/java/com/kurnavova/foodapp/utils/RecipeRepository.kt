package com.kurnavova.foodapp.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.model.RecipeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {

    private val recipeService: RecipeService by lazy { RecipeService() }

    fun getRecipeList(): LiveData<List<Recipe>> {
        val data = MutableLiveData<List<Recipe>>()

        recipeService.getRecipes().enqueue(object : Callback<RecipeList> {
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                if (response.body() == null || response.body()?.recipes == null)
                    return
                data.value = response.body()?.recipes as MutableList<Recipe>
            }

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                Log.d(TAG, "error: " + t.message.toString())
            }
        })
        return data
    }

    fun getRecipe(id: String): LiveData<Recipe> {
        val data = MutableLiveData<Recipe>()

        recipeService.getRecipe(id).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                Log.d(TAG, response.body().toString())
                data.value = response.body()
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d(TAG, "error: " + t.message.toString())
            }
        })
        return data
    }

    companion object {
        const val TAG = "RecipeRepository"
    }
}
