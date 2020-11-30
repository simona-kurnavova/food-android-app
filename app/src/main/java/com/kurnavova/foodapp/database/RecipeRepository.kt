package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.Recipe
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {

    suspend fun getRecipe(id: String): Recipe = recipeDao.getRecipe(id)

    suspend fun getRecipeList(): List<Recipe> = recipeDao.getRecipeList("italian").recipes

    companion object {
        const val TAG = "RecipeRepository"
    }
}
