package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.RandomRecipeList
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.model.RecipeList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipeDao {

    @GET("/recipes/{id}/information")
    suspend fun getRecipe(@Path("id") id: String, @Query("apiKey") apiKey: String = ApiClient.API_KEY): Recipe

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(@QueryMap queryMap: Map<String, String>, @Query("number") limit: Int = 10,
                              @Query("apiKey") apiKey: String = ApiClient.API_KEY): RecipeList

    @GET("/recipes/random")
    suspend fun getRandomRecipe(@Query("number") number: Int = 1, @Query("apiKey") apiKey: String =
        ApiClient.API_KEY): RandomRecipeList
}