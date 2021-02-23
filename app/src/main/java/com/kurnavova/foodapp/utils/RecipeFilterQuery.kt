package com.kurnavova.foodapp.utils

import android.content.Intent

/**
 * Helper class for storing filtering parameters for recipe list.
 */
class RecipeFilterQuery {

    /**
     * Map of parameters + list of values.
     */
    val map: MutableMap<String, MutableList<String>> = mutableMapOf()

    /**
     * Adds query to [name], if doesn't already exists, creates new one and stores the [value].
     */
    fun addQuery(name: String, value: String) {
        if (map.containsKey(name)) map[name]?.add(value)
        else map[name] = mutableListOf(value)
    }

    /**
     * Add or replaces query of given [name] with the [value].
     */
    fun addQueryOrReplace(name: String, value: String) {
        map[name] = mutableListOf(value)
    }

    /**
     * Fills map with the values from [intent] extras.
     */
    fun addQueryFromIntent(intent: Intent) {
        filters.forEach {
            val arg = intent.extras?.getString(it)
            if (arg != null)
                addQuery(it, arg)
        }
    }

    /**
     * Takes [map] from argument and replaces current one.
     */
    fun addQueries(map: MutableMap<String, MutableList<String>>?) {
        map?.forEach {  this.map[it.key] = it.value }
    }

    /**
     * Removes existing query with [value] and [name].
     */
    fun removeQuery(name: String, value: String) {
        map[name]?.remove(value)
        if (map[name].isNullOrEmpty()) removeQuery(name)
    }

    /**
     * Removes key of [name] from the map, together with all the values already stored.
     */
    private fun removeQuery(name: String) {
        map.remove(name)
    }

    /**
     * @return map in the form required from API, where multiple values are one string separated by comma.
     */
    fun getQuery(): MutableMap<String, String> {
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
        const val TYPE = "type"
        const val READY_TIME = "maxReadyTime"

        val filters = listOf(QUERY, CUISINE, DIET, TYPE)

        /**
         * Lists of all possible values of parameters - used as lists for saving on requests.
         */
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
    }
}