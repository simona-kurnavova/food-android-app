package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.model.RecipeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface RecipeDao {

    @GET("/recipes/{id}/information")
    suspend fun getRecipe(@Path("id") id: String, @Query("apiKey") apiKey: String = ApiClient.API_KEY): Recipe

    @GET("/recipes/complexSearch")
    suspend fun getRecipeList(@Query("cuisine") cuisine: String, @Query("apiKey") apiKey: String = ApiClient.API_KEY):
            RecipeList

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(@Query("apiKey") apiKey: String, @Query("query") query: String,
                      @Query("number") limit: Int = 10): RecipeList

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(@Query("apiKey") apiKey: String, @QueryMap queryMap: Map<String, MutableList<String>>,
                     @Query("number") limit: Int = 10): RecipeList

    @GET("/recipes/random")
    suspend fun getRandomRecipe(@Query("apiKey") apiKey: String): Recipe

    @GET("/recipes/{id}/similar")
    suspend fun getSimilarRecipes(@Path("id") id: String, @Query("apiKey") apiKey: String, @Query("limit") limit:
    String = "5"): RecipeList
}