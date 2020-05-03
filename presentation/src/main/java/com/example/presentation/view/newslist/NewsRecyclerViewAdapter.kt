package com.example.presentation.view.newslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.R
import com.example.presentation.entity.NewsUM

class NewsRecyclerViewAdapter(val context: Context, private val newsClickCallback: NewsClickCallback) : RecyclerView.Adapter<NewsViewHolder>() {

    companion object {
        val HALF_TRANSPARENT_VALUE = 0.5F
        val NOT_TRANSPARENT_VALUE = 1F
    }

    var newsList = ArrayList<NewsUM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_view_holder, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        if (!item.title.isNullOrEmpty()) {
            holder.textViewNewsTitle.text = item.title
        } else {
            holder.textViewNewsTitle.text = context.getString(R.string.empty_value)
        }
        if (!item.description.isNullOrEmpty()) {
            holder.textViewContent.text = item.description
        } else {
            holder.textViewContent.text = context.getString(R.string.empty_value)
        }
        if (!item.publishedAt.isNullOrEmpty()) {
            holder.textViewDate.text = item.publishedAt
        } else {
            holder.textViewDate.text = context.getString(R.string.empty_value)
        }
        if (!item.urlToImage.isNullOrEmpty()) {
            Glide.with(context).load(item.urlToImage)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(holder.imageViewPreview)
        } else {
            holder.imageViewPreview.setImageResource(R.drawable.ic_launcher_background)
        }

        if (item.isNewsWatched) {
            holder.itemView.alpha = HALF_TRANSPARENT_VALUE
        } else {
            holder.itemView.alpha = NOT_TRANSPARENT_VALUE
        }

        holder.itemView.setOnClickListener {
            holder.itemView.alpha = HALF_TRANSPARENT_VALUE
            newsClickCallback.onNewsClicked(item)
        }
    }


    override fun getItemCount(): Int = newsList.size

    fun addNewsList(list: List<NewsUM>) {
        this.newsList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        this.newsList.clear()
    }
}
