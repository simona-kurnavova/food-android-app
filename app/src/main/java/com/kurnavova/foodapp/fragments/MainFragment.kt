package com.kurnavova.foodapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.activities.RecipeDetailActivity
import com.kurnavova.foodapp.activities.RecipeDetailActivity.Companion.EXTRA_RECIPE_ID
import com.kurnavova.foodapp.activities.RecipeListActivity
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.CUISINE
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.TYPE
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.cuisines
import com.kurnavova.foodapp.viewmodels.RandomRecipesViewModel
import kotlinx.android.synthetic.main.content_cuisines.*
import kotlinx.android.synthetic.main.content_meals.*
import kotlinx.android.synthetic.main.content_random_recipe.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: RandomRecipesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCuisineList()
        setUpMealList()

        viewModel.recipes.observe(viewLifecycleOwner, Observer<List<Recipe>> { data ->
            val recipe = data.first() // first and only in list

            recipe_title.text = recipe.title
            recipe_time.text = getString(R.string.slider_label, recipe.readyInMinutes.toString())

            if (recipe.image != null && recipe_image != null) {
                Glide.with(this)
                    .load(recipe.image)
                    .into(recipe_image)
            }

            recipe_card.setOnClickListener {
                startActivity(Intent(requireContext(), RecipeDetailActivity::class.java).apply {
                    putExtra(EXTRA_RECIPE_ID, recipe.id)
                })
            }
        })
    }

    /**
     * Sets up meal list card with icons.
     */
    private fun setUpMealList() {
        listOf(meal_breakfast, meal_snack, meal_beverage, meal_main).forEach {
            it.setOnClickListener { view ->
                startActivity(Intent(requireContext(), RecipeListActivity::class.java).apply {
                    putExtra(TYPE, (view as TextView).text.toString())
                })
            }
        }
    }

    /**
     * Sets up cuisine chips and "more" button.s
     */
    private fun setUpCuisineList() {
        cuisines.toList().shuffled().take(CUISINE_CHIPS_COUNT).forEach {
            val chip = Chip(requireContext()).apply {
                text = it
                setOnClickListener {
                    startActivity(Intent(requireContext(), RecipeListActivity::class.java).apply {
                        putExtra(CUISINE, (it as Chip).text.toString())
                    })
                }
            }
            chip_group.addView(chip)
        }

        // "More" button - Creates dialog with all cuisine options
        more_cuisines_chip.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.cuisine)
                .setItems(cuisines.toTypedArray()) { _, which ->
                    startActivity(Intent(requireContext(), RecipeListActivity::class.java).apply {
                        putExtra(CUISINE, cuisines[which])
                    })
                }.show()
        }
    }

    companion object {
        /**
         * Number of cuisine chips shown (randomly) to avoid too much things on the screen.
         */
        const val CUISINE_CHIPS_COUNT = 10
    }
}