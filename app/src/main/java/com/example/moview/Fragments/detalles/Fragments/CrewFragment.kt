package com.example.moview.Fragments.detalles.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.Fragments.Adapters.Crew.adapter.CrewAdapter
import com.example.moview.Fragments.Adapters.Crew.model.CrewClass
import com.example.moview.R

class CrewFragment : Fragment(R.layout.fragment_crew) {
    private lateinit var progressBar: ProgressBar
    private lateinit var crewRecyclerView: RecyclerView
    private lateinit var crewAdapter: CrewAdapter
    private lateinit var crewList: MutableList<CrewClass>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressbar_crew)
        crewRecyclerView = view.findViewById(R.id.reparto_recycler)
        progressBar.visibility = View.VISIBLE
        crewList = mutableListOf()
        instanceData()
        setRecycler()
    }

    private fun setRecycler() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        crewRecyclerView!!.layoutManager = layoutManager
        crewAdapter = CrewAdapter(requireContext(), crewList)
        crewRecyclerView!!.adapter = crewAdapter
    }

    private fun instanceData() {
        progressBar.visibility = View.GONE
        var lista = (activity as DetallesTitulo).getCrew()
        lista.forEach {
            crewList.add(CrewClass(it.nombre, it.foto, it.rol))
        }
    }
}