package com.example.denis.myapplication.MainActivity.adapters.Adapter.ViewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.denis.myapplication.R

class NavHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewTitle: TextView

    init {
        textViewTitle = itemView.findViewById(R.id.text_view_title)
    }
}