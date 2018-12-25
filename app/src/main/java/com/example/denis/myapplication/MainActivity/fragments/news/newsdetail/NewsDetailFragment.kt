package com.example.denis.myapplication.MainActivity.fragments.news.newsdetail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.denis.myapplication.MainActivity.fragments.BaseFragment
import com.example.denis.myapplication.model.classes.News
import com.example.denis.myapplication.R

private const val NEWS_PARAM = "news"

class NewsDetailFragment : BaseFragment() {
    private var news: News? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable(NEWS_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rooView = inflater.inflate(R.layout.fragment_news_detail, container, false)
        prepareViews(rooView)
        return rooView
    }

    private fun prepareViews(view: View) {
        val imageViewPost = view.findViewById<View>(R.id.detail_news_image) as ImageView
        val textViewPostTitle = view.findViewById<View>(R.id.detail_news_title) as TextView
        val textViewPostCreationDate = view.findViewById<View>(R.id.detail_news_creation_date) as TextView
        val textViewPostDescription = view.findViewById<View>(R.id.detail_news_description) as TextView
        if (news != null) {
            if (!news!!.title.isNullOrEmpty()) {
                textViewPostTitle.text = news!!.title
            }
            val imageUrl = news!!.extractImageUrl()
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(imageViewPost)
            }

            if (!news!!.publishedAt.isNullOrEmpty()) {
                textViewPostCreationDate.text = news!!.publishedAt
            }
            if (!news!!.description.isNullOrEmpty()) {
                textViewPostDescription.text = Html.fromHtml(news!!.description)
            }
            textViewPostTitle.setOnClickListener {
                if (!news!!.url.isNullOrEmpty()) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news!!.url))
                    startActivity(browserIntent)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(news: News) =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NEWS_PARAM, news)
                }
            }
    }
}
