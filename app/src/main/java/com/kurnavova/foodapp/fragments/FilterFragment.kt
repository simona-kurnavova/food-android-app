package com.kurnavova.foodapp.fragments

import android.os.Bundle
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
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.filters
import com.kurnavova.foodapp.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_filter.*

/**
 * Fragment for filter - allows to choose parameters according to which app filters recipe results.
 */
class FilterFragment : Fragment() {

    private val viewModel: RecipeListViewModel by activityViewModels()

    /**
     * Temporarily stores filter parameters until user taps "Filter".
     */
    private val recipeFilterQuery = RecipeFilterQuery()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = 
        inflater.inflate(R.layout.fragment_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup filter according to already set/default values from viewmodel recipeFilterQuery instance
        recipeFilterQuery.addQueries(viewModel.getFilterQuery()?.map)

        updateChips()
        setUpDialog(meal_type_button, R.string.meal_type, resources.getStringArray(R.array.meal_types).toList(), TYPE)
        setUpDialog(diet_button, R.string.diet, resources.getStringArray(R.array.diets).toList(), DIET)
        setUpDialog(cuisine_button, R.string.cuisine, resources.getStringArray(R.array.cuisines).toList(), CUISINE)

        // Slider config
        slider.addOnChangeListener { _, value, _ ->
            recipeFilterQuery.addQueryOrReplace(READY_TIME, value.toInt().toString())
            max_prepare_time.text = getString(R.string.slider_label, value.toInt().toString())
        }

        // Filter button - refreshes list according to filters
        filter_button.setOnClickListener {
            viewModel.loadRecipes(recipeFilterQuery)
            viewModel.filtersVisible.value = false // close the fragment from activity
        }
    }

    /**
     * Updates filter chips with correct values.
     */
    private fun updateChips() {
        filter_chips.removeAllViews() // resets
        recipeFilterQuery.map.filter { filters.contains(it.key) }.forEach {
            it.value.forEach { value ->
                val chip = Chip(requireContext()).apply {
                    text = value
                    isCloseIconVisible = true
                    setOnCloseIconClickListener { view ->
                        recipeFilterQuery.removeQuery(it.key, value)
                        filter_chips.removeView(view)
                    }
                }
                filter_chips.addView(chip)
            }
        }
    }

    /**
     * Creates option material dialog with given values.
     *
     * @param view button that triggers dialog
     * @param dialogTitle title
     * @param items list of options that dialog shows
     * @param tag tag/name of parameter that is shown in the dialog for setting up filter value
     */
    private fun setUpDialog(view: View, dialogTitle: Int, items: List<String>, tag: String) {
        view.setOnClickListener {

            // filter out already chosen items to avoid duplicities
            val filteredItems = items.filter {
                !recipeFilterQuery.map.containsKey(tag) || recipeFilterQuery.map[tag]?.contains(it) == false
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