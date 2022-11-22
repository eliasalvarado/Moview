package com.example.moview.Fragments.Estrenos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moview.Activities.MainActivity
import com.example.moview.R
import com.example.moview.data.local.entity.TituloEstreno
import com.example.moview.data.local.entity.WatchModeDb
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EstrenosFragment : Fragment(R.layout.fragment_news) {
    private lateinit var adapter: EstrenosAdapter
    private lateinit var recyclerEstrenos: RecyclerView
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var progressBar: ProgressBar
    private lateinit var database: WatchModeDb
    private val viewModel: EstrenosFragmentViewModel by viewModels()

    private val estrenos: MutableList<TituloEstreno> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerEstrenos = view.findViewById(R.id.news_recycler)
        progressBar = view.findViewById(R.id.progress_estrenos)
        bottonNav = (activity as MainActivity).getToolBar()
        database = Room.databaseBuilder(
            requireContext(),
            WatchModeDb::class.java,
            "estrenosDatabase"
        ).build()

        setListeners()
        setObeservables()
        viewModel.getEstrenos()
    }

    private fun setObeservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: EstrenosFragmentUiState) {
        when(state){
            EstrenosFragmentUiState.Default -> {
                println("Default")
            }
            is EstrenosFragmentUiState.Error -> {
                println("Error")
            }
            EstrenosFragmentUiState.Loading -> {
                progressBar.visibility = View.VISIBLE
                setupRecycler()
            }
            is EstrenosFragmentUiState.Success -> {
                lifecycleScope.launch(Dispatchers.Main) {
                    delay(2000L)
                    progressBar.visibility = View.GONE
                    lifecycleScope.launch(Dispatchers.IO) {
                        saveEstrenossLocally(state.estrenos, true)
                    }
                    showEstrenos(state.estrenos, true)
                }
            }
            else -> {
                println("Error (else)")
            }
        }
    }

    private fun showEstrenos(estrenos: List<TituloEstreno>, isSync: Boolean) {
        progressBar.visibility = View.GONE
        recyclerEstrenos.visibility = View.VISIBLE
        this.estrenos.clear()
        this.estrenos.addAll(estrenos)

        if (!isSync) {
            setupRecycler()
        } else {
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecycler() {
        adapter = EstrenosAdapter(this.estrenos)
        recyclerEstrenos.layoutManager = LinearLayoutManager(requireContext())
        recyclerEstrenos.setHasFixedSize(true)
        recyclerEstrenos.adapter = adapter
    }

    private fun saveEstrenossLocally(estrenos: List<TituloEstreno>, isSync: Boolean) {
        lifecycleScope.launch {
            database.tituloDao().insertAll(estrenos)
            showEstrenos(estrenos, isSync)
        }
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(EstrenosFragmentDirections.actionNewsFragmentToSearchFragment())
                R.id.home -> requireView().findNavController().navigate(EstrenosFragmentDirections.actionNewsFragmentToHomeFragment())
                R.id.profile -> requireView().findNavController().navigate(EstrenosFragmentDirections.actionNewsFragmentToProfileFragment())
            }
            true
        }
    }
}