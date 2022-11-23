package com.example.moview.Fragments.detalles.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Activities.DetallesTitulo
import com.example.moview.Fragments.Adapters.Comments.adapter.CommentsAdapter
import com.example.moview.Fragments.Adapters.Comments.model.CommentsClass
import com.example.moview.R
import com.example.moview.data.Repository.titulo.TituloRepository
import com.example.moview.data.Repository.titulo.TituloRepositoryImpl
import com.example.moview.data.remote.firebase.FirebaseTituloApiImpl
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CommentsFragment : Fragment(R.layout.fragment_comments) {

    private lateinit var progressBar: ProgressBar
    private lateinit var commentsRecyclerView: RecyclerView
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var commentsList: MutableList<CommentsClass>
    private lateinit var repository: TituloRepository
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressbar_comments)
        commentsRecyclerView = view.findViewById(R.id.comments_recycler)
        commentsList = mutableListOf()
        repository = TituloRepositoryImpl(
            FirebaseTituloApiImpl(Firebase.firestore)
        )
        progressBar.visibility = View.VISIBLE
        instanceData()
        commentsList.forEach {
            println(it.comment+" "+it.name+" "+it.critic)
        }
        setRecycler()
    }

    private fun setRecycler() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        commentsRecyclerView!!.layoutManager = layoutManager
        commentsAdapter = CommentsAdapter(requireContext(), commentsList)
        commentsRecyclerView!!.adapter = commentsAdapter
    }

    private fun instanceData() {
            progressBar.visibility = View.GONE
            var lista = (activity as DetallesTitulo).getComentarios()
            lista.forEach{
                commentsList.add(CommentsClass(it.autor, it.comentario, it.critico))
            }
    }
}
