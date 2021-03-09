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
import androidx.recyclerview.widget.GridLayoutManager
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeDetailActivity
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import com.kurnavova.foodapp.activities.RecipeListActivity.Companion.TAG
import com.kurnavova.foodapp.adapters.RecipeListAdapter
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_search_result.*
import kotlinx.android.synthetic.main.fragment_search_result.view.*

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

    private val recipeListAdapter by lazy { RecipeListAdapter(onItemClicked) }

    /**
     * On recipe clicked open detail screen of given recipe.
     */
    private val onItemClicked: (String) -> Unit = { position ->
        val intent = Intent(activity, RecipeDetailActivity::class.java).apply {
            putExtra(EXTRA_RECIPE_ID, position)
        }
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = 
        inflater.inflate(R.layout.fragment_search_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(view.search_recycler_view) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recipeListAdapter
        }

        empty_list.visibility = View.VISIBLE

        viewModel.recipes.observe(viewLifecycleOwner) { data ->
            Log.v(TAG, "Update: $data")
            recipeListAdapter.submitList(data)
            empty_list.visibility = if (data.isNullOrEmpty()) View.VISIBLE else View.GONE
        }
        super.onViewCreated(view, savedInstanceState)
    }
}