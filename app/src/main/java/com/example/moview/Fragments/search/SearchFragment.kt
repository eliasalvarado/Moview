package com.example.moview.Fragments.search

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Search.adapter.SearchAdapter
import com.example.moview.Fragments.Adapters.Search.model.SearchClass
import com.example.moview.R
import com.example.moview.data.Repository.Estreno.EstrenoRepository
import com.example.moview.Activities.MainActivity
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.local.entity.WatchModeDb
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment: Fragment(R.layout.fragment_search), SearchAdapter.RecyclerViewSearchClickHandler {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var searchRecycler: RecyclerView
    private lateinit var list: MutableList<SearchClass>
    private lateinit var filtered: MutableList<SearchClass>
    private lateinit var repository: TituloRepository
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchInput: TextInputLayout
    private lateinit var fab: FloatingActionButton



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filtered = mutableListOf()
        searchInput = view.findViewById(R.id.search_text_input_layout)
        fab = view.findViewById(R.id.search_button)
        bottonNav = (activity as MainActivity).getToolBar()

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )

        list = mutableListOf()

        instanceData()
        setListeners()
    }

    private fun instanceData() {
        lifecycleScope.launch(Dispatchers.IO){
            val peliculas = repository.getByType("peliculas")
            if(peliculas != null){
                peliculas.forEach{
                    list.add(SearchClass(it.title, it.poster, "Pelicula", it.year, meanBoolean(it.puntaje)))
                }

            }}
        list.forEach {
            println(list.toString())
        }
        lifecycleScope.launch(Dispatchers.IO){
            val series = repository.getByType("series")
            if(series != null){
                series.forEach{
                    list.add(SearchClass(it.title, it.poster, "Pelicula", it.year, meanBoolean(it.puntaje)))
                }

            }
        }

}

    private fun setRecycler(list: MutableList<SearchClass>) {
        searchRecycler = requireView().findViewById(R.id.search_recycler)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        searchRecycler!!.layoutManager = layoutManager
        searchAdapter = SearchAdapter(requireContext(), list, this)
        searchRecycler!!.adapter = searchAdapter
    }

    private fun addPrimary(list: MutableList<Titulo>, type: String) {
        list.forEach {
            this.list.add(SearchClass(it.title, it.poster, type, it.year, meanBoolean(it.puntaje)))
        }

    }

    private fun meanBoolean(list: MutableList<Boolean>): String{
        var counter = 0.0
        val count = list.size
        list.forEach {
            if(it){
                counter++
            }
        }
        val result: Double = (counter*100)/count
        return (result.toInt().toString())
    }

    private fun setListeners(){
        fab.setOnClickListener{
            filtered.clear()
            lifecycleScope.launch(Dispatchers.IO){
                filter()
            }
            setRecycler(filtered)
        }
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.profile -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProfileFragment())
                R.id.news -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToNewsFragment())
                R.id.home -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToHomeFragment())
            }
            true
        }
    }

    private fun filter(){
        list.forEach {
            if(isRelated(searchInput.editText!!.text.toString(), it.title)){
                filtered.add(it)

            }
        }
    }

    override fun onSearchClicked(search: SearchClass) {
        println("Se ha presionado el item: "+ search.toString())
    }

    fun matchPattern(pattern: String, text: String): Int {
        var i = 0
        var j = 0
        if (pattern.isEmpty()) {
            return 0
        }
        if (text.length < pattern.length) {
            return -1
        }
        val longestPrefixSuffix = getFiniteAutomata(pattern) // DFA
        while (i < text.length) {
            if (text[i] == pattern[j]) {
                i++
                j++
                if (j == pattern.length) {
                    return i - pattern.length
                }
            } else if (j > 0) {
                j = longestPrefixSuffix[j - 1]
            } else {
                i++
            }
        }
        return -1
    }
    fun getFiniteAutomata(pattern: String): IntArray {
        var finiteAutomata = IntArray(pattern.length)
        var j = 0
        var i = 1
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {
                j++
                finiteAutomata[i] = j
                i++
            } else run {
                var temp = i - 1
                while (temp > 0) {
                    val prevLPS = finiteAutomata[temp]
                    if (pattern[i] == pattern[prevLPS]) {
                        finiteAutomata[i++] = prevLPS + 1
                        j = prevLPS
                        break
                    } else
                        temp = prevLPS - 1
                }
                if (temp <= 0) {
                    finiteAutomata[i++] = 0
                    j = 0
                }
            }
        }
        return finiteAutomata
    }

    fun isRelated(search: String, title: String): Boolean{
        return matchPattern(search.toLowerCase(), title.toLowerCase()) >= 0
    }
}