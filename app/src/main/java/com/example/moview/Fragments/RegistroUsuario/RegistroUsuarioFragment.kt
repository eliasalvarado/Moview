package com.example.moview.Fragments.RegistroUsuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.moview.R
import com.example.moview.data.Repository.image.ImageRepositoryImpl
import com.example.moview.data.Repository.user.UserRepository
import com.example.moview.data.Repository.user.UserRepositoryImpl
import com.example.moview.data.local.entity.Image
import com.example.moview.data.local.entity.User
import com.example.moview.data.remote.firebase.FirebaseImageApiImpl
import com.example.moview.data.remote.firebase.FirebaseUserApiImpl
import com.example.moview.data.remote.firebase.image.ImageRepository
import com.example.moview.viewModels.UsersViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegistroUsuarioFragment : Fragment(R.layout.fragment_registro_usuario) {
    private lateinit var yesOpt: RadioButton
    private lateinit var noOpt: RadioButton
    private lateinit var createAccountButton: Button
    private lateinit var userName: TextInputLayout
    private lateinit var correo: TextInputLayout
    private lateinit var contra1: TextInputLayout
    private lateinit var contra2: TextInputLayout
    private var esCritico = false
    private var permitido : Boolean = true
    private lateinit var repository : UserRepository
    private lateinit var repositoryImg : ImageRepository
    private lateinit var imagenPerfil : Image
    private val userViewModel: UsersViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UserRepositoryImpl(
            FirebaseUserApiImpl(Firebase.firestore)
        )
        repositoryImg = ImageRepositoryImpl(
            FirebaseImageApiImpl(Firebase.firestore)
        )
        view.apply {
            yesOpt = findViewById(R.id.radioButtonSi)
            noOpt = findViewById(R.id.radioButtonNo)
            createAccountButton = findViewById(R.id.createAccountButton)
            userName = findViewById(R.id.ingresoUsuarioregistro)
            correo = findViewById(R.id.ingresoCorreoRegistro)
            contra1 = findViewById(R.id.ingresoContraRegistro)
            contra2 = findViewById(R.id.ingresoRepetirContraRegistro)
        }

        setListeners()

    }

    private fun setListeners() {
        yesOpt.setOnClickListener {
            if(noOpt.isChecked){
                noOpt.isChecked = false
            }
            esCritico=true
        }
        noOpt.setOnClickListener {
            if(yesOpt.isChecked){
                yesOpt.isChecked = false
            }
            esCritico=false
        }
        createAccountButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                crearUsuario()
            }

        }
    }

    private fun crearUsuario() {
        val usuario = userName.editText!!.text.toString()
        val email = correo.editText!!.text.toString()
        val pasword = contra1.editText!!.text.toString()
        val pasword2 = contra2.editText!!.text.toString()
        if (usuario == "" || email == "" || pasword== "" || pasword2==""){
            Toast.makeText(activity,"Por favor llene todos los campos",Toast.LENGTH_LONG).show()
        }else{
            if(pasword.compareTo(pasword2) == 0){
                lifecycleScope.launch(Dispatchers.IO){
                    val comprobacionEmail = repository.getUserByEmail(email)
                    permitido = comprobacionEmail!!.isEmpty()
                }
                if(permitido){
                    val num = (0..30).random()
                    val id = num.toString()
                    lifecycleScope.launch(Dispatchers.IO){
                        imagenPerfil = repositoryImg.getImage(id)
                        var imagen = imagenPerfil.imagen
                        repository.createUser(
                            user = User(
                                user = usuario,
                                email = email,
                                pasword = pasword,
                                critico = esCritico,
                                perfil = imagen
                            )
                        )
                        val currentUser : User = User(
                            user = usuario,
                            email = email,
                            pasword = pasword,
                            critico = esCritico,
                            perfil = imagen
                        )
                        //aqui viewmodel
                        lifecycleScope.launch(Dispatchers.Main){
                            userViewModel.setUser(currentUser.user,currentUser.email,currentUser.pasword,
                                currentUser.critico,currentUser.perfil)
                            requireView().findNavController().navigate(
                                RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToHomeFragment()
                            )
                        }
                    }
                }else{
                    lifecycleScope.launch(Dispatchers.Main){
                        Toast.makeText(activity,"El correo está en uso",Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                lifecycleScope.launch(Dispatchers.Main){
                    Toast.makeText(activity,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show()
                }
            }

        }
    }

}