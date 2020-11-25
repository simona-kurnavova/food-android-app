package com.kurnavova.foodapp.model

import com.google.gson.annotations.SerializedName

data class RecipeList(@SerializedName("results") val recipes: List<Recipe>)