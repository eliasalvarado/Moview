package com.example.moview.fragments.InicioSesion

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.moview.R
import com.google.android.material.textfield.TextInputLayout

class InicioSesionFragment : Fragment(R.layout.fragment_inicio_sesion) {
    private lateinit var emailInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var signInButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            emailInput = findViewById(R.id.ingresoUsuarioregistro)
            passInput = findViewById(R.id.ingresoContraRegistro)
            loginButton = findViewById(R.id.botonIniciarSesion)
            signInButton = findViewById(R.id.botonRegistrarse)
        }
        setListeners()
    }

    private fun setListeners() {
        loginButton.setOnClickListener{
            requireView().findNavController().navigate(R.id.action_inicioSesionFragment_to_homeFragment)
        }
        signInButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_inicioSesionFragment_to_registroUsuarioFragment)
        }
    }

}