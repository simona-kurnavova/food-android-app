package com.kurnavova.foodapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurnavova.foodapp.data.Ingredient
import com.kurnavova.foodapp.data.Recipe

class RecipeViewModel : ViewModel() {

    private val recipes: MutableLiveData<List<Recipe>> by lazy {
        MutableLiveData<List<Recipe>>().apply {
            value = loadRecipes()
        }
    }

    fun getAllRecipes(): MutableLiveData<List<Recipe>> = recipes

    private fun loadRecipes(): List<Recipe> {
        // TODO: API call
        return listOf(
            Recipe(9, "something", 10, 2, listOf(
                Ingredient(0, "x", "imgx.png"),
                Ingredient(1, "y", "imgy.png")
            )),
            Recipe(10, "something", 10, 3, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(25, "something else", 50, 2, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(97, "something", 10, 2, listOf(
                Ingredient(0, "x", "imgx.png"),
                Ingredient(1, "y", "imgy.png")
            )),
            Recipe(11, "something", 10, 3, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(95, "something else", 50, 2, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(89, "something", 10, 2, listOf(
                Ingredient(0, "x", "imgx.png"),
                Ingredient(1, "y", "imgy.png")
            )),
            Recipe(1, "something", 10, 3, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(5, "something else", 50, 2, listOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            ))
        )
    }
}