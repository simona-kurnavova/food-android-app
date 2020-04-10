package com.kurnavova.foodapp.data

data class Recipe(val id: Int,
                  val title: String,
                  val readyInMinutes: Int?,
                  val servings: Int?,
                  val ingredients: List<Ingredient>?) {

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