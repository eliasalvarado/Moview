package com.example.moview.Fragments.Home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryItemAdapter
import com.example.moview.Fragments.Adapters.Home.adapter.CategoryRecyclerAdapter
import com.example.moview.Fragments.Adapters.Home.model.CategoryClass
import com.example.moview.Fragments.Adapters.Home.model.CategoryItem
import com.example.moview.Activities.MainActivity
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment: Fragment(R.layout.fragment_home), CategoryItemAdapter.RecyclerViewItemClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var categoryRecycler: RecyclerView
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter
    private lateinit var list: MutableList<CategoryClass>
    private lateinit var repository: TituloRepository
    private lateinit var progressBar: ProgressBar
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )

        list = mutableListOf()

        progressBar = view.findViewById(R.id.progress_home)
        progressBar.visibility = View.VISIBLE
        instanceData()
        setListeners()
        //setCategoryRecycler(list)
    }

    private fun instanceData() {
        /*
        val itemList: MutableList<CategoryItem> = ArrayList()
        itemList.add(CategoryItem("Breaking Bad 1", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))
        itemList.add(CategoryItem("Breaking Bad 2", "https://cdn.watchmode.com/posters/03173903_poster_w185.jpg"))

        val listOfAll: MutableList<CategoryClass> = ArrayList()
        listOfAll.add(CategoryClass("Romance", "Series", itemList))
        listOfAll.add(CategoryClass("Acción", "Series", itemList))

        list = listOfAll

         */

        val generosPelis: MutableList<String> = ArrayList()
        generosPelis.add("Thriller")
        generosPelis.add("Action")
        generosPelis.add("Crime")
        generosPelis.add("Comedy")
        generosPelis.add("Horror")
        generosPelis.add("Adventure")
        generosPelis.add("Science Fiction")
        generosPelis.add("Fantasy")
        generosPelis.add("Romance")


        val generosSeries: MutableList<String> = ArrayList()
        generosSeries.add("Drama")
        generosSeries.add("Western")
        generosSeries.add("Action")
        generosSeries.add("Adventure")
        generosSeries.add("Documentary")
        generosSeries.add("Mystery")

        val lista: MutableList<Titulo> = ArrayList()
        lifecycleScope.launch(Dispatchers.IO) {
            generosPelis.forEach {
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

            generosSeries.forEach {
                val seriesGenero = repository.getSeriesByGender(it)
                if (seriesGenero != null) {
                    if(seriesGenero.isNotEmpty()) {
                        seriesGenero.forEach {
                            lista.add(it)
                        }
                        agregarTitulo(lista, it, "Serie")
                        lista.clear()
                    }
                }
            }

            lifecycleScope.launch(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                setCategoryRecycler(list)
            }
        }

    }

    private fun agregarTitulo(lista: MutableList<Titulo>, genero: String, tipo: String) {
        val titulos: MutableList<CategoryItem> = ArrayList()
        lista.forEach {
            titulos.add(CategoryItem(it.id, it.title, it.poster))
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
        val intent = Intent(activity, DetallesTitulo::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }



}