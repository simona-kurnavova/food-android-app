package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeFilterQuery

/**
 * Repository for Recipes.
 */
class RecipeRepository(private val recipeDao: RecipeDao) {

    suspend fun getRecipe(id: String): Recipe =
        recipeDao.getRecipe(id)

    suspend fun searchRecipes(filterQuery: RecipeFilterQuery) : List<Recipe> =
        recipeDao.searchRecipes(filterQuery.getQuery()).recipes

    suspend fun getRandomRecipes(): List<Recipe> =
        recipeDao.getRandomRecipe().recipes
}
