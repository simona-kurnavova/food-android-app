package com.kurnavova.foodapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.adapters.RecipeListAdapter
import com.kurnavova.foodapp.data.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // setup recycler view
        val recipeAdapter = RecipeListAdapter()
        with(recipe_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        // Observer for list of recipes
        viewModel.getAllRecipes().observe(viewLifecycleOwner, Observer<List<Recipe>>{ data ->
            recipeAdapter.submitList(data)
            Log.d(TAG, "List updated: $data")
        })
    }

    companion object {
        private const val TAG = "RECIPES"
    }
}