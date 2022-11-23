package com.example.moview.Fragments.Adapters.Home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Home.model.CategoryClass
import com.example.moview.Fragments.Adapters.Home.model.CategoryItem
import com.example.moview.Fragments.Home.HomeFragment
import com.example.moview.R

class CategoryRecyclerAdapter (private val context: Context, private val listOfAll: MutableList<CategoryClass>, requireFragment: HomeFragment):
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {
    var fragment = requireFragment
    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var categoryTitle: TextView
        var typeTitle: TextView
        var itemRecycler: RecyclerView

        init{
            categoryTitle = itemView.findViewById(R.id.recycler_parent_title)
            typeTitle = itemView.findViewById(R.id.recycler_parent_title_type)
            itemRecycler = itemView.findViewById(R.id.recycler_child)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.parent_recycler_item,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryTitle.text = listOfAll[position].title
        holder.typeTitle.text = listOfAll[position].type
        setCategoryItemRecycler(holder.itemRecycler, listOfAll[position].categoryItem, fragment)
    }

    override fun getItemCount(): Int {
        return listOfAll.size
    }

    private fun setCategoryItemRecycler(recyclerView: RecyclerView, categoryItem: MutableList<CategoryItem>, requireFragment: HomeFragment){
        val itemRecyclerAdapter = CategoryItemAdapter(context, categoryItem, requireFragment)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter

    }
}