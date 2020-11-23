package com.kurnavova.foodapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kurnavova.foodapp.R
import com.kurnavova.foodapp.data.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*


class RecipeListAdapter(listener: (Int) -> Unit) :
    ListAdapter<Recipe, RecipeListAdapter.ViewHolder>(RecipeDiffCallback()) {

    private val clickCallback: (position: Int) -> Unit = {
        position -> listener(getItem(position).id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recipe, parent, false), clickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View, clickCallback: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { clickCallback(adapterPosition) }
        }

        fun bind(recipe: Recipe) {
            itemView.title.text = recipe.title
        }
    }

    companion object {
        private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}
