package com.example.moview.Fragments.Profile

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.room.Room
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.moview.MainActivity
import com.example.moview.R
import com.example.moview.data.local.source.Database
import com.example.moview.datasource.model.UserTable
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_perfil) {
    private lateinit var bottonNav: BottomNavigationView
    private lateinit var userText: TextView
    private lateinit var imagenPerfil : ImageView
    private lateinit var iconoVerificado : ImageView
    private lateinit var botonCerrarSesion: ImageButton
    private lateinit var database: Database
    private lateinit var currentUser : List<UserTable>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottonNav = (activity as MainActivity).getToolBar()
        view.apply {
            userText = findViewById(R.id.usuariotextPerfilCritico)
            imagenPerfil = findViewById(R.id.imagenPerfilCritico)
            iconoVerificado = findViewById(R.id.icono_verificado)
            botonCerrarSesion = findViewById(R.id.botonCerrarSesion)
        }
        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            currentUser = database.userDao().getTheUser()
            CoroutineScope(Dispatchers.Main).launch {
                userText.text = currentUser[0].user
                imagenPerfil.load(currentUser[0].perfil){
                    transformations(CircleCropTransformation())
                    memoryCachePolicy(CachePolicy.ENABLED)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
                if(currentUser[0].critico)
                    iconoVerificado.visibility = View.VISIBLE
                else
                    iconoVerificado.visibility = View.GONE
            }
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
        botonCerrarSesion.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                database.userDao().deleteAll()
            }
            val action =ProfileFragmentDirections.actionPerfilFragmentToInicioSesionFragment()
            requireView().findNavController().navigate(action)
        }
    }

}