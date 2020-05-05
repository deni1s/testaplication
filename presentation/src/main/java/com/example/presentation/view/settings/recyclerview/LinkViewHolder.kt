package com.example.presentation.view.settings.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R

class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewType: TextView
    val textViewUrl: TextView

    init {
        textViewType = itemView.findViewById(R.id.link_type)
        textViewUrl = itemView.findViewById(R.id.link_url)
    }
}
