package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {

    suspend fun getRecipe(id: String): Recipe =
        recipeDao.getRecipe(id)

    suspend fun searchRecipes(filterQuery: RecipeFilterQuery) : List<Recipe> =
        recipeDao.searchRecipes(filterQuery.getQuery()).recipes

    suspend fun getRandomRecipes(): List<Recipe> =
        recipeDao.getRandomRecipe().recipes
}
