package com.kurnavova.foodapp.data

data class Recipe(val id: Int,
                  val title: String,
                  val readyInMinutes: Int?,
                  val servings: Int?,
                  val ingredients: Array<Ingredient>?) {

    fun getImageUrl(size: Size = Size.SMALL): String {
        return "$BASE_URL$id-$size.jpg"
    }

    companion object {
        const val BASE_URL = "https://spoonacular.com/recipeImages/"
        enum class Size(val size: String) {
            SMALL("90x90"),
            MEDIUM("240x150"),
            BIG("480x360")
        }
    }
}