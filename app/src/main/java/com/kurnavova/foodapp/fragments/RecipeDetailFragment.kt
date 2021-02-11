package com.kurnavova.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeListActivity
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.DIET
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.linear_layout

class RecipeDetailFragment: Fragment() {

    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.recipe.observe(viewLifecycleOwner, Observer<Recipe>{ data ->
            Log.d(TAG, "Detail updated: $data")
            recipe_name.text = data.title

            //recipe_chips
            recipe_time_chip.text = "${data.readyInMinutes} min"
            recipe_servings_chip.text = "${data.servings.toString()} servings"

            data.diets.forEach { recipe_chips.addView(
                Chip(requireContext()).apply {
                    text = it
                    setOnClickListener {
                        startActivity(Intent(requireContext(), RecipeListActivity::class.java).apply {
                            putExtra(DIET, text.toString())
                        })
                    }
                })
            }

            // Strip html tags - some recipes are html, some plain text, this is easiest option
            recipe_summary.text = HtmlCompat.fromHtml(data.summary.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
                .toString()

            data?.ingredients?.forEach {
                val textView = TextView(requireContext()).apply {
                    text = it.originalString
                }
                linear_layout.addView(textView)
            }
        })
    }

    companion object {
        const val TAG = "RECIPE_DETAIL"
    }
}