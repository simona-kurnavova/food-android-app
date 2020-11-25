package com.kurnavova.foodapp.model

data class Recipe(val id: String,
                  val title: String,
                  val readyInMinutes: Int?,
                  val servings: Int?,
                  val instructions: String?,
                  val image: String?,
                  val analyzedInstructions: List<AnalysedInstructions>?,
                  val ingredients: List<Ingredient>,
                  val dishTypes: List<String>,
                  val diets: List<String>)