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
import com.example.moview.datasource.api.RetrofitInstance
import com.example.moview.datasource.model.APIresponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment(R.layout.fragment_home), CategoryItemAdapter.RecyclerViewItemClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private lateinit var list: MutableList<CategoryClass>
    private lateinit var secondaryList: MutableList<CategoryItem>

    private val key = "NJvzEz4BNnsCPqmLwIiNtXjuW1jfLW4hTYYr0Prq"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        instanceData()
        setListeners()
        setCategoryRecycler(list)
        cargarAPI()

    }

    private fun instanceData() {
        val itemList: MutableList<CategoryItem> = ArrayList()
        itemList.add(CategoryItem("Breaking Bad 1", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 2", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 3", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 4", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 5", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 6", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
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
        println("Se ha presionado el item: "+ item.toString())
    }

    private fun cargarAPI() {

        RetrofitInstance.api.getMovies().enqueue(object: Callback<APIresponse> {
            override fun onResponse(call: Call<APIresponse>, response: Response<APIresponse>) {
                if (response.isSuccessful && response.body() != null) {
                    var moviesListAPI = response.body()!!.titles
                    println(response.body())
                }
            }

            override fun onFailure(call: Call<APIresponse>, t: Throwable) {
                println("Error")
            }

        })
    }

}