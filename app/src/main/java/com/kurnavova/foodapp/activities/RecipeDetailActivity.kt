package com.kurnavova.foodapp.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.utils.NetworkUtils
import com.kurnavova.foodapp.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.activity_detail.*

/**
 * Activity representing recipe detail screen.
 */
class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel.id.value = intent.extras?.getString(EXTRA_RECIPE_ID)

        setSupportActionBar(findViewById(R.id.toolbar)) // set toolbar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        viewModel.recipe.observe(this, { data ->
            // Add image to toolbar, must be done from activity
            if (data.image != null && recipe_image != null) {
                Glide.with(this)
                    .load(data.image)
                    .into(recipe_image)
            }
        })

        if (!NetworkUtils.isConnected(application)) {
            NetworkUtils.showNetworkErrorDialog(this) { finish() }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Extras key for recipe id.
         */
        const val EXTRA_RECIPE_ID = "RECIPE_ID"
    }
}