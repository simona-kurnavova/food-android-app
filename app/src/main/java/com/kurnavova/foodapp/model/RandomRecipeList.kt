package com.kurnavova.foodapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RandomRecipeList(@SerializedName("recipes") val recipes: List<Recipe>)