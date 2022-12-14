package com.example.moview.Fragments.comentarioUsuario

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Comentario
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.local.source.Database
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.example.moview.datasource.model.UserTable
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class comentarioUsuarioFragment : Fragment(R.layout.fragment_comentario_usuario) {
    private lateinit var buttonLike: ImageButton
    private lateinit var buttonDislike: ImageButton
    private lateinit var textInputComentario: TextInputLayout
    private lateinit var buttonPublicarcomentario: Button

    private lateinit var repository: TituloRepository
    private lateinit var tituloActual: Titulo
    private var leGusta: Boolean = false
    private var calificacionSeleccionada: Boolean = false
    private lateinit var database: Database
    private lateinit var currentUser : List<UserTable>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )
        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()

        buttonLike = view.findViewById(R.id.botonhappyfaceComentario)
        buttonDislike =  view.findViewById(R.id.botonsadfaceComentario)
        textInputComentario = view.findViewById(R.id.ingresoComentario)
        buttonPublicarcomentario = view.findViewById(R.id.botonPublicarComentario)

        tituloActual = (activity as DetallesTitulo).getIdTitulo()

        setListeners()
    }

    private fun setListeners() {
        buttonLike.setOnClickListener() {
            leGusta = true
            calificacionSeleccionada = true
            lifecycleScope.launch {
                buttonLike.alpha = 1F
                buttonDislike.alpha = 0.30F
            }
        }

        buttonDislike.setOnClickListener() {
            leGusta = false
            calificacionSeleccionada = true
            lifecycleScope.launch {
                buttonLike.alpha = 0.30F
                buttonDislike.alpha = 1F
            }
        }

        buttonPublicarcomentario.setOnClickListener() {
            if(calificacionSeleccionada) {
                publicarComentario()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor, seleccione una calificaci??n para la pel??cula/serie",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun publicarComentario() {
        val comentarioTextInput: String = textInputComentario.editText?.text.toString() ?: ""
        val puntajeActual: MutableList<Boolean> = tituloActual.puntaje
        puntajeActual.add(leGusta)
        val datosActuales: Map<String, MutableList<Boolean>> = mapOf(
            "puntaje" to puntajeActual
        )

        lifecycleScope.launch(Dispatchers.IO) {
            currentUser = database.userDao().getTheUser()
            val puntajeExitoso = repository.actualizarPuntajeTitulo(tituloActual.id, datosActuales)
            if(textInputComentario.editText?.text.toString() != "") {
                val comentarioPublicar = Comentario(
                    autor = currentUser[0].user,
                    comentario = comentarioTextInput,
                    critico = currentUser[0].critico
                )
                val comentarioExitoso = repository.actualizarComentariosTitulo(tituloActual.id, comentarioPublicar)
                lifecycleScope.launch(Dispatchers.Main) {
                    if(puntajeExitoso && comentarioExitoso) {
                        Toast.makeText(
                            requireContext(),
                            "Tu comentario ha sido publicado con ??xito",
                            Toast.LENGTH_LONG
                        ).show()
                        (activity as DetallesTitulo).actualizarDatos()
                        textInputComentario.editText?.setText("")
                        buttonLike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                        buttonDislike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                        calificacionSeleccionada = false
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Ha ocurrido un error inesperado",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                lifecycleScope.launch(Dispatchers.Main) {
                    if(puntajeExitoso) {
                        Toast.makeText(
                            requireContext(),
                            "Tu puntuacion ha sido publicado con ??xito",
                            Toast.LENGTH_LONG
                        ).show()
                        textInputComentario.editText?.setText("")
                        (activity as DetallesTitulo).actualizarDatos()
                        buttonLike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                        buttonDislike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                        calificacionSeleccionada = false
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Ha ocurrido un error inesperado",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }
    }

}