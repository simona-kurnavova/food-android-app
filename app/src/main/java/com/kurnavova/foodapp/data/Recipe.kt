package com.kurnavova.foodapp.data

import com.google.gson.annotations.SerializedName

data class Recipe(@SerializedName("id") val id: Int,
                  @SerializedName("title") val title: String,
                  @SerializedName("readyInMinutes") val readyInMinutes: Int?,
                  @SerializedName("servings") val servings: Int?,
                  @SerializedName("extendedIngredients") val ingredients: List<Ingredient>) {

    val smallImageUrl: String get() = getImageUrl(Size.SMALL)

    val mediumImageUrl: String get() = getImageUrl(Size.MEDIUM)

    val bigImageUrl: String get() = getImageUrl(Size.BIG)

    private fun getImageUrl(size: Size = Size.SMALL): String {
        return "$BASE_URL$id-${size.size}.jpg"
    }

    companion object {
        private const val BASE_URL = "https://spoonacular.com/recipeImages/"
        private enum class Size(val size: String) {
            SMALL("90x90"),
            MEDIUM("240x150"),
            BIG("480x360")
        }
    }
}