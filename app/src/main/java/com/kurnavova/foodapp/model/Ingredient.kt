package com.kurnavova.foodapp.model

import androidx.annotation.Keep

@Keep
data class Ingredient(val id: Int,
                      val name: String,
                      val image: String,
                      val amount: Double,
                      val unit: String,
                      val originalString: String)