package com.example.moview.Fragments.detalles.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SinopsisFragment : Fragment(R.layout.fragment_sinopsis) {
    private lateinit var sinopsisText: TextView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sinopsisText = view.findViewById(R.id.sinopsisTextViewFragmentSinopsis)
        progressBar = view.findViewById(R.id.progressbarFragmentSinopsis)

        setData()
    }

    private fun setData() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000L)
            progressBar.visibility = View.GONE
            sinopsisText.visibility = View.VISIBLE
            sinopsisText.text = (activity as DetallesTitulo).getSinopsisTitulo()
        }
    }


}