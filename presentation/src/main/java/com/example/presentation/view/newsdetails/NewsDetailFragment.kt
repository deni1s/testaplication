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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.R
import com.example.presentation.entity.NewsUM
import com.example.presentation.utils.fragment.BaseFragment
import com.example.presentation.view.settings.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import javax.inject.Inject

class NewsDetailFragment : BaseFragment() {

    private lateinit var rootView: View
    private val args: NewsUM by navArgs()
    private lateinit var presenter: NewsDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_news_detail, container, false)
        rootView.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        presenter = ViewModelProviders.of(this, modelFactory)[NewsDetailViewModel::class.java]
        presenter.setNewsDetails(args)
        return rootView
    }


    override fun bindSubscriptions() {
        presenter.setShowNewsDetailsListener(this::showNewsDetail)
    }

    private fun showNewsDetail(newsDetail: NewsUM) {
        val imageViewPost = rootView.findViewById<View>(R.id.detail_news_image) as ImageView
        val textViewPostTitle = rootView.findViewById<View>(R.id.detail_news_title) as TextView
        val textViewPostDescription =
            rootView.findViewById<View>(R.id.detail_news_description) as TextView
        textViewPostTitle.text = newsDetail.title
        val imageUrl = newsDetail.urlToImage
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this).load(imageUrl)
                .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                .into(imageViewPost)
        }

        textViewPostDescription.text = Html.fromHtml(newsDetail.description)
        textViewPostTitle.setOnClickListener {
            if (!newsDetail.url.isNullOrEmpty()) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newsDetail.url))
                startActivity(browserIntent)
            }
        }
    }
}

