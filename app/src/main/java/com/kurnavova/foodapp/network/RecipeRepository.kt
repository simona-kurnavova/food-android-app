package com.kurnavova.foodapp.network

import android.util.Log
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import java.lang.Exception

/**
 * Repository for Recipes.
 */
class RecipeRepository(private val recipeDao: RecipeDao) {

    suspend fun getRecipe(id: String): Recipe? {
        val recipe = null
        try {
           return recipeDao.getRecipe(id)
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
        }
        return recipe
    }

    suspend fun searchRecipes(filterQuery: RecipeFilterQuery) : List<Recipe> {
        var recipes = listOf<Recipe>()
        try {
            recipes = recipeDao.searchRecipes(filterQuery.getQuery()).recipes
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
        }
        return recipes
    }

    suspend fun getRandomRecipes(): List<Recipe> {
        var recipes = listOf<Recipe>()
        try {
            recipes = recipeDao.getRandomRecipe().recipes
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
        }
        return recipes
    }

    companion object {
        const val TAG = "Repository"
    }
}