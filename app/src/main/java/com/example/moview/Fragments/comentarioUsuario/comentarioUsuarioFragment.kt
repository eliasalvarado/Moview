package com.example.moview.Fragments.comentarioUsuario

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )

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
                buttonLike.setBackgroundColor(resources.getColor(R.color.teal_700))
                buttonDislike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            }
        }

        buttonDislike.setOnClickListener() {
            leGusta = false
            calificacionSeleccionada = true
            lifecycleScope.launch {
                buttonLike.setBackgroundColor(resources.getColor(R.color.teal_700))
                buttonDislike.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            }
        }

        buttonPublicarcomentario.setOnClickListener() {
            publicarComentario()
        }
    }

    private fun publicarComentario() {
        val comentario: String = textInputComentario.editText?.text.toString() ?: ""
        val puntajeActual: MutableList<Boolean> = tituloActual.puntaje
        puntajeActual.add(leGusta)
        val datosActuales: Map<String, MutableList<Boolean>> = mapOf(
            "puntaje" to puntajeActual
        )

        lifecycleScope.launch(Dispatchers.IO) {
            val comentario = repository.actualizarPuntajeTitulo(tituloActual.id, datosActuales)
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    requireContext(),
                    "Tu comentario ha sido publicado con éxito",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}