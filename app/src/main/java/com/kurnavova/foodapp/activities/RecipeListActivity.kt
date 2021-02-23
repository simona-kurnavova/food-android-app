package com.kurnavova.foodapp.activities

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.utils.NetworkUtils
import com.kurnavova.foodapp.utils.RecipeFilterQuery
import com.kurnavova.foodapp.utils.RecipeFilterQuery.Companion.QUERY
import com.kurnavova.foodapp.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipeListActivity : AppCompatActivity() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        handleIntent(intent)

        setSupportActionBar(findViewById(R.id.toolbar)) // set toolbar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getString(R.string.app_name)
        }

        with(viewModel.filtersVisible) {
            value = false // default
            observe(this@RecipeListActivity, Observer {
                filter_container.visibility = if (it) VISIBLE else GONE
            })
        }

        if (!NetworkUtils.isConnected(application)) {
            NetworkUtils.showNetworkErrorDialog(this) { finish() }
        }
    }

    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        handleIntent(intent)
        super.onNewIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Log.d(TAG, "Searching for $query")
                viewModel.filterQuery.value = RecipeFilterQuery().apply { addQuery(QUERY, query) }
            }
        } else {
            // intent to filter
            Log.d(TAG, "Filtering")
            viewModel.filterQuery.value = RecipeFilterQuery().apply { addQueryFromIntent(intent) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) {
            filter_container.visibility = if (filter_container.visibility == VISIBLE) GONE else VISIBLE
            viewModel.filtersVisible.value = filter_container.visibility == VISIBLE
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.recipe_list_menu, menu)
        return true
    }

    companion object {
        const val TAG = "Recipe list"
    }
}