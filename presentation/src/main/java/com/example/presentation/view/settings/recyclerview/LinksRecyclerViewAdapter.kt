package com.example.presentation.view.settings.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.entity.LinkUM

class LinksRecyclerViewAdapter(
    private val linkClickCallback: LinkClickCallback
) : RecyclerView.Adapter<LinkViewHolder>() {
    var linkList = ArrayList<LinkUM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.link_list_item, parent, false)
        return LinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        val item = linkList[position]
        holder.textViewType.text = item.type.toString()
        holder.textViewUrl.text = item.link

        holder.itemView.setOnClickListener {
            linkClickCallback.onLinkClicked(item)
        }
    }

    fun addLinkList(list: List<LinkUM>) {
        this.linkList.addAll(list)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int = linkList.size
}

