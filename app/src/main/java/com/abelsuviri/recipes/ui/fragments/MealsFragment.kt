package com.abelsuviri.recipes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.abelsuviri.recipes.R
import com.abelsuviri.recipes.ui.adapter.MealsAdapter
import com.abelsuviri.viewmodel.MealsViewModel
import kotlinx.android.synthetic.main.fragment_meals.view.*
import kotlinx.android.synthetic.main.fragment_meals.view.loadingLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Abel Suviri
 */

class MealsFragment : Fragment() {

    private val mealsViewModel: MealsViewModel by viewModel()
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_meals, container, false)
        initViews()
        subscribeLiveData()
        val category = arguments?.getString("category")
        mealsViewModel.getMeals(category!!)

        return rootView
    }

    /**
     * Set the RecyclerView properties
     */
    private fun initViews() {
        rootView.mealsList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rootView.mealsList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    /**
     * Start to observe the LiveData values from the ViewModel
     */
    private fun subscribeLiveData() {
        mealsViewModel.mealsListLiveData.observe(viewLifecycleOwner, Observer {
            rootView.loadingLayout.visibility = View.GONE
            rootView.mealsList.adapter = MealsAdapter(it)
        })
    }
}
