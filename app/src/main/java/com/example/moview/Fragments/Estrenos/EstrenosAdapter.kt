package com.example.moview.Fragments.Estrenos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.moview.R
import com.example.moview.data.local.entity.TituloEstreno

class EstrenosAdapter(
    private val dataSet: MutableList<TituloEstreno>,
) : RecyclerView.Adapter<EstrenosAdapter.ViewHolder>() {

    class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        private val layoutEstreno: ConstraintLayout = view.findViewById(R.id.layout_news)
        private val imageEstreno: ImageView = view.findViewById(R.id.posterPeliculaNews)
        private val textTitleEstreno: TextView = view.findViewById(R.id.tituloPeliculaTextNews)
        private val textFechaestreno: TextView = view.findViewById(R.id.fechaPeliculaTextNews)

        fun setData(estreno: TituloEstreno) {
            estreno.apply {
                imageEstreno.load(estreno.imagen) {
                    placeholder(R.drawable.downloading_icon)
                    error(R.drawable.error_icon)
                    memoryCachePolicy(CachePolicy.ENABLED)
                    diskCachePolicy(CachePolicy.READ_ONLY)
                    transformations(RoundedCornersTransformation(25.0F))
                }
                textTitleEstreno.text = title
                textFechaestreno.text = fecha
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_news, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}