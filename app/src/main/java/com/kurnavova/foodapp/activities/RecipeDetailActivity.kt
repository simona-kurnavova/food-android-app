package com.kurnavova.foodapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.fragments.RecipeDetailFragment

class RecipeDetailActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DETAIL_FRAGMENT_ACT", "onCreate")
        setContentView(R.layout.activity_recipe_detail)

        val extras: Bundle? = intent.extras
        val bundle = Bundle()
        if (extras != null) {
            bundle.putInt(EXTRA_RECIPE_ID, extras.getInt(EXTRA_RECIPE_ID))
        }
        val fragment = RecipeDetailFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .add(R.id.detail_fragment, fragment)
            .commit()
    }

    companion object {
        const val EXTRA_RECIPE_ID = "RECIPE_ID"
    }
}