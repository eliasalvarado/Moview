package com.example.moview.Activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import coil.load
import coil.request.CachePolicy
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.local.entity.Titulo
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetallesTitulo : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var banner: ImageView
    private lateinit var poster: ImageView
    private lateinit var buttonTrailer: ImageButton
    private lateinit var buttomBack: ImageButton
    private lateinit var buttonCalificar: ImageButton
    private lateinit var nombreTitulo: TextView
    private lateinit var datosTitulo: TextView
    private lateinit var calificacionTitulo: TextView
    private lateinit var buttonSinopsis: Button
    private lateinit var buttonReparto: Button
    private lateinit var buttonComentarios: Button
    private lateinit var tituloActual: Titulo

    private lateinit var repository: TituloRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_titulo)

        banner = findViewById(R.id.cartelPeliculaDetalles)
        poster = findViewById(R.id.posterPeliculaDetalles)
        buttonTrailer =  findViewById(R.id.imgageButtonPlayActivityDestalles)
        buttomBack = findViewById(R.id.botonBackDetalles)
        buttonCalificar = findViewById(R.id.botonCalificaPeliculaDetalles)
        nombreTitulo = findViewById(R.id.textviewPreguntaTitulo)
        datosTitulo = findViewById(R.id.datosPelicula)
        calificacionTitulo = findViewById(R.id.porcentajeDeLikesDetalle)

        buttonSinopsis = findViewById(R.id.sinopsisFragmentDetalles)
        buttonReparto = findViewById(R.id.repartoFragmentDetalles)
        buttonComentarios = findViewById(R.id.comentariosFragmentDetalles)

        val id: String = intent.getStringExtra("id") ?: "184033"

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainer_DetallesTituloActivity
        ) as NavHostFragment
        navController = navHostFragment.navController

        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )



        inicializarDatos(id)
        setListeners()
    }

    private fun inicializarDatos(id: String) {
        lifecycleScope.launch {
            buttonCalificar.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            buttonSinopsis.setBackgroundColor(resources.getColor(R.color.teal_700))
            buttonReparto.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            buttonComentarios.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val titulo = repository.getById(id = id)
            if(titulo != null) {
                tituloActual = titulo
                lifecycleScope.launch(Dispatchers.Main) {
                    banner.load(titulo.banner){
                        memoryCachePolicy(CachePolicy.DISABLED)
                        diskCachePolicy(CachePolicy.DISABLED)
                        crossfade(enable = true)
                        crossfade(450)
                        placeholder(R.drawable.downloading_icon)
                        error(R.drawable.error_icon)
                    }
                    poster.load(titulo.poster){
                        memoryCachePolicy(CachePolicy.DISABLED)
                        diskCachePolicy(CachePolicy.DISABLED)
                        crossfade(enable = true)
                        crossfade(450)
                        placeholder(R.drawable.downloading_icon)
                        error(R.drawable.error_icon)
                    }
                    nombreTitulo.text = titulo.title
                    val detalles: String = titulo.fecha_estreno + " - Director"
                    datosTitulo.text = detalles
                    val porcentaje: String = porcentajePuntaje(titulo.puntaje).toString() + " %"
                    calificacionTitulo.text = porcentaje
                }
            }
        }
    }

    private fun porcentajePuntaje(puntajes: MutableList<Boolean>): Int {
        val cantidadTotal = puntajes.size
        var conteo = 0
        puntajes.forEach {
            if(it) {
                conteo += 1
            }
        }
        return (conteo * 100) / cantidadTotal
    }


    private fun setListeners() {
        buttonTrailer.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tituloActual.trailer))
            startActivity(intent)
        }

        buttonSinopsis.setOnClickListener() {
            lifecycleScope.launch {
                navController.navigate(R.id.sinopsisFragment)
                buttonCalificar.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonSinopsis.setBackgroundColor(resources.getColor(R.color.teal_700))
                buttonReparto.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonComentarios.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            }
        }

        buttonReparto.setOnClickListener() {
            lifecycleScope.launch {
                navController.navigate(R.id.crewFragment)
                buttonCalificar.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonSinopsis.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonReparto.setBackgroundColor(resources.getColor(R.color.teal_700))
                buttonComentarios.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            }
        }

        buttonComentarios.setOnClickListener() {
            lifecycleScope.launch {
                navController.navigate(R.id.commentsFragment)
                buttonCalificar.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonSinopsis.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonReparto.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonComentarios.setBackgroundColor(resources.getColor(R.color.teal_700))
            }
        }

        buttonCalificar.setOnClickListener() {
            lifecycleScope.launch {
                navController.navigate(R.id.comentarioUsuarioFragment)
                buttonCalificar.setBackgroundColor(resources.getColor(R.color.teal_700))
                buttonSinopsis.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonReparto.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
                buttonComentarios.setBackgroundColor(resources.getColor(R.color.colorTextInputs))
            }
        }

        buttomBack.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            this.onBackPressed()
        }
    }

    fun getSinopsisTitulo(): String {
        return tituloActual.sinopsis
    }

    fun getIdTitulo(): Titulo {
        return tituloActual
    }
}