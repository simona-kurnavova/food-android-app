package com.kurnavova.foodapp.fragments

import android.content.Intent
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
import com.kurnavova.foodapp.activities.RecipeDetailActivity
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import com.kurnavova.foodapp.adapters.RecipeListAdapter
import com.kurnavova.foodapp.data.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import com.kurnavova.foodapp.listeners.RecyclerItemClickListener


class RecipeListFragment : Fragment(), RecyclerItemClickListener.OnItemClickListener {

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
        recipe_recycler_view.addOnItemTouchListener(
            RecyclerItemClickListener(requireActivity(), recipe_recycler_view, this))

        // Observer for list of recipes
        viewModel.getAllRecipes().observe(viewLifecycleOwner, Observer<List<Recipe>>{ data ->
            recipeAdapter.submitList(data)
            Log.d(TAG, "List updated: $data")
        })
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, "tapped on position $position")
        //view.recipe_name.text
        val intent = Intent(view.context, RecipeDetailActivity::class.java).apply {
            putExtra(EXTRA_RECIPE_ID, position)
        }
        startActivity(intent)
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG, "long tapped on position $position")
    }

    companion object {
        private const val TAG = "RECIPES"
    }
}