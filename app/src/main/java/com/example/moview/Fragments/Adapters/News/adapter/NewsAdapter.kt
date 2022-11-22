package com.example.moview.Fragments.Adapters.News.adapter

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
import com.example.moview.Fragments.Adapters.News.model.NewsClass
import com.example.moview.R

class NewsAdapter(
    private val context: Context,
    private val newsList: MutableList<NewsClass>,
    private val listener: RecyclerViewNewsClickHandler
) : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {
    class NewsItemViewHolder(itemView: View, private val listener: RecyclerViewNewsClickHandler): RecyclerView.ViewHolder(itemView){
        var image: ImageView
        var title: TextView
        var date: TextView
        var desc: TextView
        var layoutItem: ConstraintLayout
        init{
            image = itemView.findViewById(R.id.item_news_image)
            title = itemView.findViewById(R.id.item_news_title)
            date = itemView.findViewById(R.id.item_news_date)
            desc = itemView.findViewById(R.id.item_news_desc)
            layoutItem = itemView.findViewById(R.id.news_item)
        }
    }

    interface RecyclerViewNewsClickHandler{
        fun onNewsClicked(news: NewsClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false), listener)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.image.load(newsList[position].imageURL){
            memoryCachePolicy(CachePolicy.DISABLED)
            diskCachePolicy(CachePolicy.DISABLED)
            crossfade(enable = true)
            crossfade(450)
            placeholder(R.drawable.downloading_icon)
            error(R.drawable.error_icon)
        }
        holder.title.text = newsList[position].title
        holder.date.text = newsList[position].date
        holder.desc.text = newsList[position].description
        holder.layoutItem.setOnClickListener {
            listener.onNewsClicked(newsList[position])
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}