package com.example.moview.Fragments.Adapters.Comments.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moview.Fragments.Adapters.Comments.model.CommentsClass
import com.example.moview.R
import org.w3c.dom.Text

class CommentsAdapter(
    private val context: Context,
    private val commentList: MutableList<CommentsClass>
): RecyclerView.Adapter<CommentsAdapter.CommentsItemViewHolder>() {
    class CommentsItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var comment: TextView
        var verified: ImageView
        init{
            name = itemView.findViewById(R.id.autorComentario)
            comment = itemView.findViewById(R.id.comentario)
            verified = itemView.findViewById(R.id.iconVerificado)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsItemViewHolder {
        return CommentsItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_comentario, parent, false))
    }

    override fun onBindViewHolder(holder: CommentsItemViewHolder, position: Int) {
        if(commentList[position].critic){
            holder.verified.visibility = View.VISIBLE
        }else{
            holder.verified.visibility = View.GONE
        }
        holder.name.text = commentList[position].name
        holder.comment.text = commentList[position].comment
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}