package com.example.moview.Fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moview.Activities.MainActivity
import com.example.moview.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment: Fragment(R.layout.fragment_search) {
    private lateinit var bottonNav: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        setListeners()
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.profile -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProfileFragment())
                R.id.news -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToNewsFragment())
                R.id.home -> requireView().findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToHomeFragment())
            }
            true
        }
    }
}