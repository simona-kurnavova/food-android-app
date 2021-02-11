package com.kurnavova.foodapp.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.model.Recipe
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Activity representing recipe detail screen.
 */
class DetailActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(findViewById(R.id.toolbar)) // set toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false) // remove title

        viewModel.id.value = intent.extras?.getString(EXTRA_RECIPE_ID)

        viewModel.recipe.observe(this, Observer<Recipe> { data ->
            // Add image to toolbar, must be done from activity
            if (data.image != null && recipe_image != null) {
                Glide.with(this)
                    .load(data.image)
                    .into(recipe_image)
            }
        })
    }

    companion object {
        /**
         * Extras key for recipe id.
         */
        const val EXTRA_RECIPE_ID = "RECIPE_ID"
    }
}