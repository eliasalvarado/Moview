package com.example.moview.Fragments.Profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moview.MainActivity
import com.example.moview.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment(R.layout.fragment_perfil) {
    private lateinit var bottonNav: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()

        setListeners()
    }

    private fun setListeners(){
        bottonNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.search -> requireView().findNavController().navigate(ProfileFragmentDirections.actionPerfilFragmentToSearchFragment())
                R.id.news -> requireView().findNavController().navigate(ProfileFragmentDirections.actionPerfilFragmentToNewsFragment())
                R.id.home -> requireView().findNavController().navigate(ProfileFragmentDirections.actionPerfilCriticoFragmentToHomeFragment())
            }
            true
        }
    }

}