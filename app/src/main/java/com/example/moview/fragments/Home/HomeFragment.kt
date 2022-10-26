package com.example.moview.fragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moview.MainActivity
import com.example.moview.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var bottonNav: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        setListeners()
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
                R.id.news -> requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment())
                R.id.profile -> requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
            }
            true
        }
    }

}