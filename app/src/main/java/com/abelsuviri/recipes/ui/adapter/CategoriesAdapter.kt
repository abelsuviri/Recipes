package com.abelsuviri.recipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abelsuviri.data.model.CategoriesList
import com.abelsuviri.recipes.R
import com.abelsuviri.recipes.ui.adapter.viewholder.CategoriesViewHolder
import com.abelsuviri.recipes.ui.adapter.viewholder.ItemClick

/**
 * @author Abel Suviri
 */

class CategoriesAdapter constructor(private val categoriesList: CategoriesList, private val itemClick: ItemClick)
    : RecyclerView.Adapter<CategoriesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CategoriesViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = categoriesList.categories[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return categoriesList.categories.size
    }
}