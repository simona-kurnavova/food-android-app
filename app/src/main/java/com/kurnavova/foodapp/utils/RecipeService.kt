package com.kurnavova.foodapp.utils

import com.google.gson.GsonBuilder
import com.kurnavova.foodapp.database.RecipeDao
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RecipeService {

    private val recipeServiceApi: RecipeDao = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(RecipeDao::class.java)

    //fun getRecipes() = recipeServiceApi.getRecipeList(API_KEY, "italian")

    //fun getRecipe(id: String) = recipeServiceApi.getRecipe(id, API_KEY)

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val API_KEY = "a1785ee75385455dbfae0ae10841d2f4"
    }
}