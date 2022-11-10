package com.example.moview.Fragments.InicioSesion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.moview.R
import com.example.moview.data.local.entity.User
import com.example.moview.data.remote.firebase.FirebaseUserApiImpl
import com.example.moview.data.repository.auth.user.UserRepository
import com.example.moview.data.repository.auth.user.UserRepositoryImpl
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InicioSesionFragment : Fragment(R.layout.fragment_inicio_sesion) {
    private lateinit var nameUserInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var signInButton: Button
    private lateinit var repository : UserRepository
    private var permitido = true
    private lateinit var currentUser : User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            nameUserInput = findViewById(R.id.ingresoUsuarioregistro)
            passInput = findViewById(R.id.ingresoContraRegistro)
            loginButton = findViewById(R.id.botonIniciarSesion)
            signInButton = findViewById(R.id.botonRegistrarse)
        }
        repository = UserRepositoryImpl(
            FirebaseUserApiImpl(Firebase.firestore)
        )
        setListeners()
    }

    private fun setListeners() {
        loginButton.setOnClickListener{
            val name = nameUserInput.editText!!.text.toString()
            val contra = passInput.editText!!.text.toString()

            iniciarsesion(name, contra)
            requireView().findNavController().navigate(
                InicioSesionFragmentDirections.actionInicioSesionFragmentToHomeFragment()
            )
        }
        signInButton.setOnClickListener {

            requireView().findNavController().navigate(
                InicioSesionFragmentDirections.actionInicioSesionFragmentToRegistroUsuarioFragment()
            )
        }
    }

    private fun iniciarsesion(user: String, pasword:String) {
        lifecycleScope.launch(Dispatchers.IO){
            val result = repository.getUser(user)
            if (result == null){
                permitido = false
            }else{
                permitido = true
                currentUser = result[0]
            }
        }
        if(permitido){
            if(currentUser.pasword.compareTo(pasword)==0){
                //acceso permitido
            }else{
                Toast.makeText(activity,"Nombre de usuario o contrasela invalido", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(activity,"Nombre de usuario o contrasela invalido", Toast.LENGTH_LONG).show()
        }

    }


}