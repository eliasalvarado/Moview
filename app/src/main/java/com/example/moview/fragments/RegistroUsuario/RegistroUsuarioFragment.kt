package com.example.moview.fragments.RegistroUsuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.navigation.findNavController
import com.example.moview.R


class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {
    private lateinit var yesOpt: RadioButton
    private lateinit var noOpt: RadioButton
    private lateinit var createAccountButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            yesOpt = findViewById(R.id.radioButtonSi)
            noOpt = findViewById(R.id.radioButtonNo)
            createAccountButton = findViewById(R.id.createAccountButton)
        }
        setListeners()
    }

    private fun setListeners() {
        yesOpt.setOnClickListener {
            if(noOpt.isChecked){
                noOpt.isChecked = false
            }
        }
        noOpt.setOnClickListener {
            if(yesOpt.isChecked){
                yesOpt.isChecked = false
            }
        }
        createAccountButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_registroUsuarioFragment_to_homeFragment)
        }
    }
}