package com.example.moview.Fragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryItemAdapter
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryRecyclerAdapter
import com.example.moview.Fragments.Adapters.Home.model.CategoryClass
import com.example.moview.Fragments.Adapters.Home.model.CategoryItem
import com.example.moview.MainActivity
import com.example.moview.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment: Fragment(R.layout.fragment_home), CategoryItemAdapter.RecyclerViewItemClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private lateinit var list: MutableList<CategoryClass>
    private lateinit var secondaryList: MutableList<CategoryItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        instanceData()
        setListeners()
        setCategoryRecycler(list)
    }

    private fun instanceData() {
        val itemList: MutableList<CategoryItem> = ArrayList()
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        val listOfAll: MutableList<CategoryClass> = ArrayList()
        listOfAll.add(CategoryClass("Romance", "Series", itemList))
        listOfAll.add(CategoryClass("Acción", "Series", itemList))
        listOfAll.add(CategoryClass("Suspenso", "Series", itemList))
        listOfAll.add(CategoryClass("Romance", "Peliculas", itemList))
        listOfAll.add(CategoryClass("Acción", "Peliculas", itemList))
        listOfAll.add(CategoryClass("Suspenso", "Peliculas", itemList))
        listOfAll.add(CategoryClass("Terror", "Peliculas", itemList))
        listOfAll.add(CategoryClass("Thriller", "Peliculas", itemList))
        list = listOfAll
    }

    private fun setCategoryRecycler(listOfAll: MutableList<CategoryClass>) {
        categoryRecycler = requireView().findViewById(R.id.parent_recycler_view_home)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        categoryRecycler!!.layoutManager = layoutManager
        categoryRecyclerAdapter = CategoryRecyclerAdapter(requireContext(), listOfAll, this)
        categoryRecycler!!.adapter = categoryRecyclerAdapter
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                )
                R.id.news -> requireView().findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNewsFragment()
                )
                R.id.profile -> requireView().findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                )
            }
            true
        }
    }

    override fun onItemClicked(item: CategoryItem) {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetallesFragment())
    }

}