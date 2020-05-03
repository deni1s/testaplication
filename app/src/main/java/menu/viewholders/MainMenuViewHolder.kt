package com.example.app.menu.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R

class MainMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewMenuItemTitle: TextView
    val textViewUnreadMessages: TextView

    init {
        textViewMenuItemTitle = itemView.findViewById(R.id.text_view_menu_title)
        textViewUnreadMessages = itemView.findViewById(R.id.text_view_messages_indicator)
    }
}
