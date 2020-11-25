package com.kurnavova.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeDetailActivity
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import com.kurnavova.foodapp.adapters.RecipeListAdapter
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

    private val recipeListAdapter by lazy { RecipeListAdapter(onItemClicked) }

    /**
     * On recipe clicked.
     */
    private val onItemClicked: (String) -> Unit = { position ->
        Log.d(TAG, "tapped on position $position")
        val intent = Intent(activity, RecipeDetailActivity::class.java).apply {
            putExtra(EXTRA_RECIPE_ID, position)
        }
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Setup recycler view
        with(recipe_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeListAdapter
        }

        empty_list.visibility = VISIBLE

        // Observer for list of recipes
        viewModel.getAllRecipes().observe(viewLifecycleOwner, Observer<List<Recipe>>{ data ->
            recipeListAdapter.submitList(data)
            empty_list.visibility = if (data.isNullOrEmpty()) VISIBLE else GONE
        })

    }

    companion object {
        private const val TAG = "RECIPE_LIST"
    }
}