package com.kurnavova.foodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment: Fragment() {

    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.id.value = arguments?.getString(EXTRA_RECIPE_ID)

        // Observer for recipe
        viewModel.recipe.observe(viewLifecycleOwner, Observer<Recipe>{ data ->
            Log.d(TAG, "Detail updated: $data")

            recipe_name.text = data.title
            recipe_time.text = data.readyInMinutes.toString() + " min"

            recipe_text.text = data.instructions

            if (data.image != null && recipe_image != null) {
                Glide.with(this)
                    .load(data.image)
                    .into(recipe_image)
            }
        })
    }

    companion object {
        const val TAG = "RecipeDetailFragment"
    }
}