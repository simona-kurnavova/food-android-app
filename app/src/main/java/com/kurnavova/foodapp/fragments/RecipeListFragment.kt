package com.kurnavova.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.adapters.RecipeListAdapter
import com.kurnavova.foodapp.data.Ingredient
import com.kurnavova.foodapp.data.Recipe

class RecipeListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        val recipes: List<Recipe> = listOf(
            Recipe(0, "something", 10, 2, arrayOf(
                Ingredient(0, "x", "imgx.png"),
                Ingredient(1, "y", "imgy.png")
            )),
            Recipe(1, "something", 10, 3, arrayOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            )),
            Recipe(2, "something else", 50, 2, arrayOf(
                Ingredient(1, "y", "imgy.png"),
                Ingredient(3, "z", "imgz.png")
            ))
        )

        val recipeAdapter = RecipeListAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipe_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = recipeAdapter
        }

        recipeAdapter.submitList(recipes)
        return view
    }
}