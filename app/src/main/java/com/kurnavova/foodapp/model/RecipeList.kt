package com.kurnavova.foodapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RecipeList(@SerializedName("results") val recipes: List<Recipe>)