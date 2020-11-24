package com.kurnavova.foodapp.data

import com.google.gson.annotations.SerializedName

data class Recipe(@SerializedName("id") val id: Int,
                  @SerializedName("title") val title: String,
                  @SerializedName("readyInMinutes") val readyInMinutes: Int?,
                  @SerializedName("servings") val servings: Int?,
                  @SerializedName("instructions") val instructions: String?,
                  @SerializedName("image") val image: String?,
                  //@SerializedName("analyzedInstructions") val analyzedInstructions: String?,
                  @SerializedName("extendedIngredients") val ingredients: List<Ingredient>,
                  @SerializedName("dishTypes") val dishTypes: List<String>,
                  @SerializedName("diets") val diets: List<String>)