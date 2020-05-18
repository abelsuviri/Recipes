package com.abelsuviri.recipes.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abelsuviri.data.model.MealCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * @author Abel Suviri
 */

class CategoriesViewHolder constructor(view: View, private val itemClick: ItemClick) : RecyclerView.ViewHolder(view) {
    fun bind(category: MealCategory) {
        itemView.itemTextView.text = category.name
        Picasso.get()
            .load(category.thumbnail)
            .fit()
            .into(itemView.pictureImageView)

        itemView.setOnClickListener { itemClick.onItemClick(category.name) }
    }
}