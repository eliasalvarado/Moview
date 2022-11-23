package com.example.moview.Fragments.Adapters.Crew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.moview.Fragments.Adapters.Crew.model.CrewClass
import com.example.moview.R

class CrewAdapter(
    private val context: Context,
    private val crewList: MutableList<CrewClass>
): RecyclerView.Adapter<CrewAdapter.CrewItemViewHolder>() {
    class CrewItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var role: TextView
        var image: ImageView
        init{
            image = itemView.findViewById(R.id.fotoActor)
            name = itemView.findViewById(R.id.nombreActor)
            role = itemView.findViewById(R.id.rolActor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewItemViewHolder {
        return CrewItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_reparto, parent, false))
    }

    override fun onBindViewHolder(holder: CrewItemViewHolder, position: Int) {
        holder.image.load(crewList[position].image){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
        }
        holder.role.text = crewList[position].role
        holder.name.text = crewList[position].name
    }

    override fun getItemCount(): Int {
        return crewList.size
    }
}