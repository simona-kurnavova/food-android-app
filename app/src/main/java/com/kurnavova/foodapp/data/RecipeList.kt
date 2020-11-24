package com.kurnavova.foodapp.data

import com.google.gson.annotations.SerializedName

data class RecipeList(@SerializedName("results") val recipes: List<Recipe>)