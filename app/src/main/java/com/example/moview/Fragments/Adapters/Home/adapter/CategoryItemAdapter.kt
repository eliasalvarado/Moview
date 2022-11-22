package com.example.moview.Fragments.Adapters.Home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.moview.Fragments.Adapters.Home.model.CategoryItem
import com.example.moview.R

class CategoryItemAdapter(private val context: Context, private val categoryList: MutableList<CategoryItem>, private val listener: RecyclerViewItemClickHandler): RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {
    class CategoryItemViewHolder(itemView: View,
    private val listener: RecyclerViewItemClickHandler): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var layoutItem: ConstraintLayout

        init{
            image = itemView.findViewById(R.id.child_image_item)
            title = itemView.findViewById(R.id.child_title_item)
            layoutItem = itemView.findViewById(R.id.child_recycler_item)
        }
    }

    interface RecyclerViewItemClickHandler{
        fun onItemClicked(item: CategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.child_recycler_item, parent, false), listener)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.image.load(categoryList[position].imageURL){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
        }
        holder.title.text = categoryList[position].contentTitle
        holder.layoutItem.setOnClickListener {
            listener.onItemClicked(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}