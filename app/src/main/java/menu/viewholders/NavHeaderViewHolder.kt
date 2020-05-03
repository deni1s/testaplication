package com.example.app.menu.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R

class NavHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewTitle: TextView

    init {
        textViewTitle = itemView.findViewById(R.id.text_view_title)
    }
}
