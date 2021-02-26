package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.model.RandomRecipeList
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.model.RecipeList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Data access object for web API of Spoonacular.
 */
interface RecipeDao {

    /**
     * Requests recipe with given [id].
     */
    @GET("/recipes/{id}/information")
    suspend fun getRecipe(@Path("id") id: String, @Query("apiKey") apiKey: String = ApiClient.API_KEY): Recipe

    /**
     * Requests recipes that fulfill queryMap arguments. Default limit is [RESULT_COUNT_LIMIT].
     */
    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap queryMap: Map<String, String>, @Query("number") limit: Int = RESULT_COUNT_LIMIT,
        @Query("apiKey") apiKey: String = ApiClient.API_KEY
    ): RecipeList

    /**
     * Requests random recipes, default number is [RANDOM_RESULT_COUNT].
     */
    @GET("/recipes/random")
    suspend fun getRandomRecipe(
        @Query("number") number: Int = RANDOM_RESULT_COUNT, @Query("apiKey") apiKey: String =
            ApiClient.API_KEY
    ): RandomRecipeList

    companion object {
        /**
         * Number of maximum recipes that are returned after [searchRecipes]. Low number is chosen due to limited
         * number of requests.
         */
        const val RESULT_COUNT_LIMIT = 15

        /**
         * Number of random recipes returned after calling [getRandomRecipe].
         */
        const val RANDOM_RESULT_COUNT = 1
    }
}