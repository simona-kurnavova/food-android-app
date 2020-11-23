package com.kurnavova.foodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment: Fragment() {

    private var recipeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("DETAIL_FRAGMENT", "onViewCreated")
        recipeId = arguments?.getInt(EXTRA_RECIPE_ID)
        recipe_name.text = recipeId.toString()

        // Observer for recipe
        /**viewModel.getRecipe(activity?.taskId).observe(viewLifecycleOwner, Observer<Recipe>{ data ->
            Log.d(RecipeListFragment.TAG, "Detail updated: $data")
        })**/
    }
}