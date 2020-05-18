package com.abelsuviri.recipes.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abelsuviri.data.model.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * @author Abel Suviri
 */

class MealsViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(meal: Meal) {
        itemView.itemTextView.text = meal.name
        Picasso.get()
            .load(meal.thumbnail)
            .fit()
            .into(itemView.pictureImageView)
    }
}