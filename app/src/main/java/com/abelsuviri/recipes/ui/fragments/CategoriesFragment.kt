package com.abelsuviri.recipes.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abelsuviri.recipes.R
import com.abelsuviri.recipes.ui.adapter.CategoriesAdapter
import com.abelsuviri.recipes.ui.adapter.viewholder.ItemClick
import com.abelsuviri.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_categories.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Abel Suviri
 */

class CategoriesFragment : Fragment(), ItemClick {

    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private lateinit var rootView: View
    private val recyclerState = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView =  inflater.inflate(R.layout.fragment_categories, container, false)
        initViews()
        subscribeLiveData()
        return rootView
    }

    /**
     * Set the RecyclerView properties
     */
    private fun initViews() {
        rootView.categoriesList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rootView.categoriesList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        categoriesViewModel.getCategories()
    }

    /**
     * Start to observe the LiveData values from the ViewModel
     */
    private fun subscribeLiveData() {
        categoriesViewModel.categoriesListLiveData.observe(viewLifecycleOwner, Observer {
            rootView.loadingLayout.visibility = View.GONE
            rootView.categoriesList.adapter = CategoriesAdapter(it, this)
        })
    }

    /**
     * Callback from a list item click. This will open a new Fragment to show the meals for the selected category
     */
    override fun onItemClick(category: String) {
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(category)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        //Restore the list scrolling when coming back to this screen
        if (recyclerState.containsKey(RECYCLER_STATE)) {
            Handler().postDelayed(Runnable {
                rootView.categoriesList.layoutManager?.onRestoreInstanceState(recyclerState.getParcelable(RECYCLER_STATE))
            }, 50)

        }
    }

    override fun onPause() {
        super.onPause()
        //Save the scroll position when leaving this screen
        recyclerState.putParcelable(RECYCLER_STATE, rootView.categoriesList.layoutManager?.onSaveInstanceState())
    }
}

private const val RECYCLER_STATE = "RecyclerState"
