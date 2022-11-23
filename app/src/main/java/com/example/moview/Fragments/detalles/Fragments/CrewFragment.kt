package com.example.moview.Fragments.detalles.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.R

class CrewFragment : Fragment(R.layout.fragment_crew) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var detales : DetallesTitulo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.reparto_recycler)

    }

}