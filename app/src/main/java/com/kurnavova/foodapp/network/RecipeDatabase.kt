package com.kurnavova.foodapp.network

import com.kurnavova.foodapp.network.ApiClient.client

/**
 * Recipe database that provides DAO.
 */
object RecipeDatabase {
    fun provideRecipeDAO(): RecipeDao = client.create(RecipeDao::class.java)
}