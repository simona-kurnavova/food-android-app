package com.kurnavova.foodapp.data

data class Ingredient(val id: Int,
                      val name: String,
                      val image: String) {

    fun getImageUrl(size: Size = Size.SMALL): String {
        return "$BASE_URL$size/$image"
    }

    companion object {
        val BASE_URL = "https://spoonacular.com/cdn/ingredients_"
        enum class Size(size: String) {
            SMALL("100x100"),
            MEDIUM("250x250"),
            BIG("500x500")
        }
    }
}