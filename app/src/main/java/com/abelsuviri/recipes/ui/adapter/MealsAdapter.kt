package com.abelsuviri.recipes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abelsuviri.data.model.MealsList
import com.abelsuviri.recipes.R
import com.abelsuviri.recipes.ui.adapter.viewholder.MealsViewHolder

/**
 * @author Abel Suviri
 */

class MealsAdapter constructor(private val mealsList: MealsList) : RecyclerView.Adapter<MealsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MealsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val item = mealsList.meals[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mealsList.meals.size
    }
}