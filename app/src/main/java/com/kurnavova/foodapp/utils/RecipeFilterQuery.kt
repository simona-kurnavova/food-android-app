package com.kurnavova.foodapp.utils

class RecipeFilterQuery {

    private val map: MutableMap<String, String> = mutableMapOf()

    fun addQuery(name: String, values: MutableList<String>) {
        map[name] = values[0]
        values.filter { it != values[0] }.forEach { map[name] += ",$it" } // in the form of "coworker,bowling"
    }

    fun addQuery(name: String, value: String) {
        map[name] = value
    }

    fun getQuery() = map
}