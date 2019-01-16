package com.example.presentation.view.menu.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.presentation.R

class MainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewMenuItemTitle: TextView
    val textViewUnreadMessages: TextView

    init {
        textViewMenuItemTitle = itemView.findViewById(R.id.text_view_menu_title)
        textViewUnreadMessages = itemView.findViewById(R.id.text_view_messages_indicator)
    }
}