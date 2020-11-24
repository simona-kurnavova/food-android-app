package com.kurnavova.foodapp.data

import com.google.gson.annotations.SerializedName

data class Ingredient(@SerializedName("id") val id: Int,
                      @SerializedName("name") val name: String,
                      @SerializedName("image") val image: String) {

    val smallImageUrl: String get() = getImageUrl(Size.SMALL)

    val mediumImageUrl: String get() = getImageUrl(Size.MEDIUM)

    val bigImageUrl: String get() = getImageUrl(Size.BIG)

    private fun getImageUrl(size: Size = Size.SMALL): String {
        return "$BASE_URL$size/$image"
    }

    companion object {
        private const val BASE_URL = "https://spoonacular.com/cdn/ingredients_"
        private enum class Size(val size: String) {
            SMALL("100x100"),
            MEDIUM("250x250"),
            BIG("500x500")
        }
    }
}