package com.kurnavova.foodapp.model

import com.google.gson.annotations.SerializedName

data class RandomRecipeList(@SerializedName("recipes") val recipes: List<Recipe>)