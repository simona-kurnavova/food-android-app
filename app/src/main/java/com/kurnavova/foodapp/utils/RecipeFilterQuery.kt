package com.kurnavova.foodapp.utils

import android.content.Intent
import android.util.Log

class RecipeFilterQuery {

    val map: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun addQuery(name: String, value: String) {
        if (map.containsKey(name)) map[name]?.add(value)
        else map[name] = mutableListOf(value)
    }

    fun addQueryOrReplace(name: String, value: String) {
        map[name] = mutableListOf(value)
    }

    fun addQueryFromIntent(intent: Intent) {
        filters.forEach {
            val arg = intent.extras?.getString(it)
            if (arg != null)
                addQuery(it, arg)
        }
    }

    fun addQueries(queries: MutableMap<String, MutableList<String>>?) {
        if (queries == null)
            Log.d("test", "empty little fucker")
        queries?.forEach {  map[it.key] = it.value }
    }


    fun removeQuery(name: String, value: String) {
        map[name]?.remove(value)
        if (map[name].isNullOrEmpty()) removeQuery(name)
    }

    fun removeQuery(name: String) {
        map.remove(name)
    }

    fun getQuery(): MutableMap<String, String> { // in the form of "coworker,bowling"
        val query = mutableMapOf<String, String>()
        map.forEach {
            query[it.key] = it.value.first()
            it.value.filter { arg -> arg != it.value.first() }.forEach { value -> query[it.key] += ",$value"}
        }
        return query
    }

    companion object {
        /**
         * Search argument
         */
        const val QUERY = "query"
        const val CUISINE = "cuisine"
        const val DIET = "diet"
        const val INTOLERANCES = "intolerances"
        const val TYPE = "type"
        const val READY_TIME = "maxReadyTime"

        val filters = listOf(QUERY, CUISINE, DIET, INTOLERANCES, TYPE)

        val cuisines = listOf(
            "African", "American", "British", "Cajun", "Caribbean", "Chinese", "Eastern European", "European", "French",
            "German", "Greek", "Indian", "Irish", "Italian", "Japanese", "Jewish", "Korean", "Latin American",
            "Mediterranean", "Mexican", "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese"
        )

        val mealTypes = listOf(
            "main course", "side dish", "dessert", "appetizer", "salad", "bread", "breakfast", "soup", "beverage",
            "sauce", "marinade", "fingerfood", "snack", "drink"
        )

        val diets = listOf(
            "Ketogenic", "Vegetarian", "Lacto-Vegetarian", "Ovo-Vegetarian", "Vegan", "Pescetarian", "Paleo", "Primal",
            "Whole30"
        )

        val intolerancies = listOf(
            "Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame", "Shellfish", "Soy", "Sulfite", "Tree Nut",
            "Wheat"
        )
    }
}