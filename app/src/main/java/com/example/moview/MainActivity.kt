package com.example.moview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottonNav: BottomNavigationView

    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottonNav = findViewById(R.id.navigationbar)

        val navHostController = supportFragmentManager.findFragmentById(
            R.id.containerViewprincipal
        ) as NavHostFragment

        navController = navHostController.navController


        setNavigationChange()
    }



    fun getToolBar(): BottomNavigationView {
        return bottonNav
    }

    private fun setNavigationChange() {
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.inicioSesionFragment -> {
                    bottonNav.visibility = View.GONE
                }
                R.id.registroUsuarioFragment -> {
                    bottonNav.visibility = View.GONE
                }
                R.id.homeFragment -> {
                    bottonNav.visibility = View.VISIBLE
                }
                R.id.searchFragment -> {
                    bottonNav.visibility = View.VISIBLE
                }
                R.id.newsFragment -> {
                    bottonNav.visibility = View.VISIBLE
                }
                R.id.perfilFragment -> {
                    bottonNav.visibility = View.VISIBLE
                }
                R.id.editarPerfilFragment -> {
                    bottonNav.visibility = View.GONE
                }
                R.id.detallesFragment -> {
                    bottonNav.visibility = View.GONE
                }
                R.id.comentarioUsuarioFragment -> {
                    bottonNav.visibility = View.GONE
                }
            }
        }
    }
}