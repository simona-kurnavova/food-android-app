package com.kurnavova.foodapp.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.utils.NetworkUtils

/**
 * Main activity with cards and random recipe.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar)) // set toolbar
        supportActionBar?.setTitle(R.string.app_name) // set app name as title

        if (!NetworkUtils.isConnected(application)) { // show dialog when there is no internet
            NetworkUtils.showNetworkErrorDialog(this.findViewById(android.R.id.content))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }
}