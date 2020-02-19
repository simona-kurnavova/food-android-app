package com.kurnavova.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.data.Recipe
import kotlinx.android.synthetic.main.recipe_row.view.*

class RecipeListAdapter: ListAdapter<Recipe, RecipeListAdapter.ViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.recipe_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipe: Recipe) {
            itemView.title.text = recipe.title
        }
    }

}
