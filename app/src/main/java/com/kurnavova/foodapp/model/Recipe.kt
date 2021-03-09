package com.kurnavova.foodapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Recipe(val id: String,
                  val title: String,
                  val readyInMinutes: Int?,
                  val servings: Int?,
                  val summary: String?,
                  val instructions: String?,
                  val image: String?,
                  @SerializedName("extendedIngredients") val ingredients: List<Ingredient>,
                  val dishTypes: List<String>,
                  val diets: List<String>)