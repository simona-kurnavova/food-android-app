package com.kurnavova.foodapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.CUISINE
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.DIET
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.READY_TIME
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.TYPE
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.cuisines
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.diets
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.filters
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.mealTypes
import com.kurnavova.foodapp.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

    private val recipeFilterQuery = RecipeFilterQuery()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = 
        inflater.inflate(R.layout.fragment_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup filter according to already set/default values
        recipeFilterQuery.addQueries(viewModel.filterQuery.value?.map)

        updateChips()

        setUpDialog(meal_type_button, R.string.meal_type, mealTypes, TYPE)
        setUpDialog(diet_button, R.string.diet, diets, DIET)
        setUpDialog(cuisine_button, R.string.cuisine, cuisines, CUISINE)

        // Slider config
        slider.addOnChangeListener { slider, value, fromUser ->
            recipeFilterQuery.addQueryOrReplace(READY_TIME, value.toInt().toString())
            if (value.toInt() == 0) {
                recipeFilterQuery.removeQuery(READY_TIME)
                max_prepare_time.text = getString(R.string.slider_label, getString(R.string.slider_argument_any))
            }
            max_prepare_time.text = getString(R.string.slider_label, value.toInt().toString())
        }

        // Filter button
        filter_button.setOnClickListener {
            // refreshes list according to filters
            viewModel.filterQuery.value = recipeFilterQuery
            viewModel.filtersVisible.value = false // close the fragment from activity
        }
    }

    private fun updateChips() {
        filter_chips.removeAllViews()
        recipeFilterQuery.map.forEach { query ->
            if (filters.contains(query.key)) {
                query.value.forEach {
                    val chip = Chip(requireContext()).apply {
                        text = it
                        isCloseIconVisible = true
                        setOnCloseIconClickListener{
                            recipeFilterQuery.removeQuery(query.key, (it as Chip).text.toString())
                            filter_chips.removeView(it)
                        }
                    }
                    filter_chips.addView(chip)
                }
            }
        }
    }

    private fun setUpDialog(view: View, dialogTitle: Int, items: List<String>, tag: String) {
        view.setOnClickListener {

            // filter out already chosen items to avoid duplicities
            val filteredItems = items.filter {
                !recipeFilterQuery.map.containsKey(tag) || !recipeFilterQuery.map[tag]!!.contains(it)
            }

            // Create dialog with options
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(dialogTitle)
                .setItems(filteredItems.toTypedArray()) { _, which ->
                    recipeFilterQuery.addQuery(tag, filteredItems[which])
                    updateChips()
                }.show()
        }
    }
}