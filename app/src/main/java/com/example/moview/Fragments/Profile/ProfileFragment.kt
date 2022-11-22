package com.example.moview.Fragments.Profile

import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.moview.MainActivity
import com.example.moview.R
import com.example.moview.viewModels.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_perfil) {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var userText: TextView
    private lateinit var imagenPerfil : ImageView
    private lateinit var iconoVerificado : ImageView
    private val userViewModel: UsersViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()
        view.apply {
            userText = findViewById(R.id.usuariotextPerfilCritico)
            imagenPerfil = findViewById(R.id.imagenPerfilCritico)
            iconoVerificado = findViewById(R.id.icono_verificado)
        }
        userViewModel.viewModelScope.launch {
            if(userViewModel.getCritico())
                Toast.makeText(activity,"Si", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(activity,"No",Toast.LENGTH_LONG).show()
        }




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