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

    @GET("/recipes/random")
    fun getRandomRecipe(@Query("apiKey") apiKey: String): Call<Recipe>

    @GET("/recipes/complexSearch")
    fun searchRecipes(@Query("apiKey") apiKey: String, @Query("query") query: String): Call<RecipeList>

    // cuisine, includeIngredients, excludeIngredients, maxReadyTime, type, number (of results), offset (The number
    // of results to
    // skip (between 0 and 900).), ignorePantry (Whether to ignore typical pantry items, such as water, salt, flour),

    @GET("/recipes/complexSearch")
    fun filterRecipes(@Query("apiKey") apiKey: String, @Query("query") query: String): Call<RecipeList>

    @GET("/recipes/complexSearch")
    fun getRecipeList(@Query("apiKey") apiKey: String, @Query("cuisine") cuisine: String): Call<RecipeList>

    // https://api.spoonacular.com/recipes/{id}/similar
    @GET("/recipes/{id}/similar")
    fun getSimilarRecipes(@Path("id") id: String, @Query("apiKey") apiKey: String, @Query("limit") limit: String =
        "5"): Call<RecipeList>

    // TODO: filtering of recipes
    // TODO: random recipe
}