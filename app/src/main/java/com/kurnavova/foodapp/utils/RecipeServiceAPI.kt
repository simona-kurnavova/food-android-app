package com.kurnavova.foodapp.utils

import com.kurnavova.foodapp.data.Recipe
import com.kurnavova.foodapp.data.RecipeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeServiceAPI {
    @GET("/recipes/{id}/information")
    fun getRecipe(@Path("id") id: String, @Query("apiKey") apiKey: String): Call<Recipe>

    @GET("/recipes/complexSearch")
    fun getRecipeList(@Query("apiKey") apiKey: String, @Query("cuisine") cuisine: String): Call<RecipeList>

    // TODO: filtering of recipes
    // TODO: random recipe
}