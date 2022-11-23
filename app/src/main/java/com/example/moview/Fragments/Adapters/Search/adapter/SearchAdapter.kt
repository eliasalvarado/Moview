package com.example.moview.Fragments.Adapters.Search.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.moview.Fragments.Adapters.Search.model.SearchClass
import com.example.moview.R
import org.w3c.dom.Text

class SearchAdapter (
    private val context: Context,
    private val searchList: MutableList<SearchClass>,
    private val listener: RecyclerViewSearchClickHandler
): RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder>(){
    class SearchItemViewHolder(itemView: View, private val listener: RecyclerViewSearchClickHandler): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var type: TextView
        var rate: TextView
        var layout: ConstraintLayout
        init{
            image = itemView.findViewById(R.id.image_itemCharacter_category)
            title = itemView.findViewById(R.id.text_item_name)
            type = itemView.findViewById(R.id.text_item_type)
            rate = itemView.findViewById(R.id.text_item_ranking)
            layout = itemView.findViewById(R.id.layout_itemSearch)
        }
    }

    interface RecyclerViewSearchClickHandler{
        fun onSearchClicked(search: SearchClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_search, parent, false), listener)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.image.load(searchList[position].imageURL){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
        }
        holder.title.text = searchList[position].title+" ("+searchList[position].year+")"
        holder.rate.text = searchList[position].score+"%"
        holder.type.text = searchList[position].type
        holder.layout.setOnClickListener {
            listener.onSearchClicked(searchList[position])
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

}