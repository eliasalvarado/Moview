package com.example.moview.Fragments.InicioSesion

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.moview.R
import com.example.moview.data.Repository.user.UserRepository
import com.example.moview.data.Repository.user.UserRepositoryImpl
import com.example.moview.data.local.entity.User
import com.example.moview.data.remote.firebase.FirebaseUserApiImpl
import com.example.moview.viewModels.UsersViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class InicioSesionFragment : Fragment(R.layout.fragment_inicio_sesion) {
    private lateinit var nameUserInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var signInButton: Button
    private lateinit var repository : UserRepository
    private var permitido = true
    private lateinit var currentUser : User
    private val userViewModel: UsersViewModel by activityViewModels()

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

        }
        signInButton.setOnClickListener {

            requireView().findNavController().navigate(
                InicioSesionFragmentDirections.actionInicioSesionFragmentToRegistroUsuarioFragment()
            )
        }
    }

    private fun iniciarsesion(email: String, pasword:String) {
        lifecycleScope.launch(Dispatchers.IO){
            val result = repository.getUserByEmail(email)
            if (result!!.size == 0){
                permitido = false
                //Toast.makeText(activity,"Ha ocurrido un error inesperado", Toast.LENGTH_LONG).show()
            }else{
                currentUser = result[0]
                permitido = true
            }
            comprobarInicioSesion(email, pasword)
        }


    }

    private fun comprobarInicioSesion(email: String, pasword:String) {
        if(permitido){
            if(currentUser.pasword.compareTo(pasword)==0){
                //acceso permitido
                lifecycleScope.launch(Dispatchers.Main) {
                    requireView().findNavController().navigate(
                        InicioSesionFragmentDirections.actionInicioSesionFragmentToHomeFragment()
                    )
                    //aqui view model
                    userViewModel.viewModelScope.launch {
                        userViewModel.setName(currentUser.user)
                    }
                }
            }else{
                lifecycleScope.launch(Dispatchers.Main){
                    Toast.makeText(activity,"Nombre de usuario o contrasela invalido", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            lifecycleScope.launch(Dispatchers.Main){
                Toast.makeText(activity,"Nombre de usuario o contrasela invalido", Toast.LENGTH_LONG).show()
            }
        }
    }

}