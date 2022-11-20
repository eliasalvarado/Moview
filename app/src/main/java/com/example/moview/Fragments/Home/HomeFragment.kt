package com.example.moview.Fragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryItemAdapter
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryRecyclerAdapter
import com.example.moview.Fragments.Adapters.Home.model.CategoryClass
import com.example.moview.Fragments.Adapters.Home.model.CategoryItem
import com.example.moview.MainActivity
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.example.moview.datasource.api.RetrofitInstance
import com.example.moview.datasource.model.APIresponse
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment: Fragment(R.layout.fragment_home), CategoryItemAdapter.RecyclerViewItemClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private lateinit var list: MutableList<CategoryClass>
    private lateinit var secondaryList: MutableList<CategoryItem>
    private lateinit var repository: TituloRepository

    private val key = "NJvzEz4BNnsCPqmLwIiNtXjuW1jfLW4hTYYr0Prq"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )

        instanceData()
        setListeners()
        setCategoryRecycler(list)
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

        val generos: MutableList<String> = ArrayList()
        generos.add("Thriller")
        generos.add("Action")
        generos.add("Crime")
        generos.add("Comedy")




        val lista: MutableList<Titulo> = ArrayList()

        lifecycleScope.launch(Dispatchers.IO) {
            generos.forEach {
                val pelisGenero = repository.getPeliculasByGender(it)
                if(pelisGenero != null) {
                    pelisGenero.forEach {
                        lista.add(it)
                    }
                    agregarTitulo(lista, it, "Pelicula")
                    lista.clear()
                }
            }

            lista.clear()

            generos.forEach {
                val seriesGenero = repository.getSeriesByGender(it)
                if(seriesGenero != null) {
                    seriesGenero.forEach {
                        lista.add(it)
                    }
                    agregarTitulo(lista, it, "Serie")
                    lista.clear()
                }
            }




            lifecycleScope.launch(Dispatchers.Main) {
                setCategoryRecycler(list)
            }



            /*
            val peliculas = repository.getByType("peliculas")
            val series = repository.getByType("series")

            val prueba: MutableList<CategoryItem> = ArrayList()
            if (peliculas != null) {
                peliculas.forEach {
                    prueba.add(CategoryItem(it.title, it.poster))
                }
                listOfAll.add(CategoryClass("Prueba", "Peliculas", prueba))
            }

            val pruebaSeries: MutableList<CategoryItem> = ArrayList()
            if (series != null) {
                series.forEach {
                    pruebaSeries.add(CategoryItem(it.title, it.poster))
                }
                listOfAll.add(CategoryClass("Prueba2", "Series", pruebaSeries))
            }

            lifecycleScope.launch(Dispatchers.Main) {
                setCategoryRecycler(listOfAll)
            }

             */

            println("Se han cargado")
        }

    }

    private fun agregarTitulo(lista: MutableList<Titulo>, genero: String, tipo: String) {
        val titulos: MutableList<CategoryItem> = ArrayList()
        lista.forEach {
            titulos.add(CategoryItem(it.title, it.poster))
        }
        list.add(CategoryClass(genero, tipo, titulos))
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



}