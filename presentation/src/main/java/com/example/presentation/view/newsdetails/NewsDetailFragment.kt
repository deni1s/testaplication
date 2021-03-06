package com.example.presentation.view.newsdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.R
import com.example.presentation.entity.NewsUM
import com.example.presentation.routing.NEWS_PARAM
import com.example.presentation.utils.fragment.showToast
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import org.koin.android.ext.android.inject

class NewsDetailFragment : Fragment(), NewsDetailContract.View {

    private lateinit var rootView: View
    private var news: NewsUM? = null
    override val presenter: NewsDetailContract.Presenter by inject()

    override fun showNewsDetail(newsDetail: NewsUM) {
        val imageViewPost = rootView.findViewById<View>(R.id.detail_news_image) as ImageView
        val textViewPostTitle = rootView.findViewById<View>(R.id.detail_news_title) as TextView
        val textViewPostCreationDate =
            rootView.findViewById<View>(R.id.detail_news_creation_date) as TextView
        val textViewPostDescription =
            rootView.findViewById<View>(R.id.detail_news_description) as TextView
        if (news != null) {
            textViewPostTitle.text = news!!.title
            val imageUrl = news!!.urlToImage
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this).load(imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(imageViewPost)
            }

            textViewPostCreationDate.text = news!!.publishedAt
            textViewPostDescription.text = Html.fromHtml(news!!.description)
            textViewPostTitle.setOnClickListener {
                if (!news!!.url.isNullOrEmpty()) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news!!.url))
                    startActivity(browserIntent)
                }
            }
        }
    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable(NEWS_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_news_detail, container, false)
        presenter.subscribe(this)
        rootView.toolbar.setNavigationOnClickListener { presenter.popBackStack() }
        presenter.setNewsDetails(news!!)
        return rootView
    }
}
