package com.example.moview.Fragments.InicioSesion

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.moview.R
import com.example.moview.data.Repository.user.UserRepository
import com.example.moview.data.Repository.user.UserRepositoryImpl
import com.example.moview.data.local.entity.User
import com.example.moview.data.remote.firebase.FirebaseUserApiImpl
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.moview.data.local.source.Database
import com.example.moview.datasource.model.UserTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first

class InicioSesionFragment : Fragment(R.layout.fragment_inicio_sesion) {
    private lateinit var nameUserInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var signInButton: Button
    private lateinit var repository : UserRepository
    private var permitido = true
    private lateinit var currentUser : User
    private lateinit var database: Database
    private lateinit var loggedUser : List<UserTable>


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
        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()

        CoroutineScope(Dispatchers.IO).launch{
            loggedUser = database.userDao().getTheUser()
            if(loggedUser.isNotEmpty()){
                CoroutineScope(Dispatchers.Main).launch {
                    requireView().findNavController().navigate(
                        InicioSesionFragmentDirections.actionInicioSesionFragmentToHomeFragment()
                    )
                }
            }
        }
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
                    //db
                    agregarADb(currentUser)
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

    private fun agregarADb(user: User){
        val LogedUser = UserTable(
            id = 1,
            user = user.user,
            email = user.email,
            pasword = user.pasword,
            critico = user.critico,
            perfil = user.perfil
        )
        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().insert(LogedUser)
        }
    }

}