package com.example.denis.myapplication.MainActivity.fragments.news

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.denis.myapplication.R

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewNewsTitle: TextView
    val textViewDate: TextView
    val textViewContent: TextView
    val imageViewPreview: ImageView

    init {
        textViewNewsTitle = itemView.findViewById(R.id.text_view_title)
        textViewContent = itemView.findViewById(R.id.text_view_content)
        textViewDate = itemView.findViewById(R.id.text_view_date)
        imageViewPreview = itemView.findViewById(R.id.image_view_preview)
    }
}