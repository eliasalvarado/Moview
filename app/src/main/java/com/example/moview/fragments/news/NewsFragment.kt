package com.example.moview.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.moview.MainActivity
import com.example.moview.R
import com.example.moview.fragments.Home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView


class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var bottonNav: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        setListeners()
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToSearchFragment())
                R.id.home -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToHomeFragment())
                R.id.profile -> requireView().findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToProfileFragment())
            }
            true
        }
    }

}