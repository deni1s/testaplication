package com.example.presentation.view.newslist

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.presentation.view.BaseFragment
import com.example.presentation.utils.recyclerview.EndlessScroll
import com.example.presentation.R
import com.example.presentation.entity.NewsUM
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import kotlinx.android.synthetic.main.progress_bar.*
import org.koin.android.ext.android.inject

class NewsListFragment : BaseFragment(), NewsListContract.View, NewsClickCallback {
    private var recyclerView: RecyclerView? = null
    private var textViewEmpty: TextView? = null
    private var newsAdapter: NewsRecyclerViewAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var rootView: View? = null
    var shouldRefreshNews: Boolean = false
    lateinit var endlessScrollListener: EndlessScroll

    override val presenter: NewsListContract.Presenter by inject()

    override fun showError(error: String) {
        hideProgressBar()
        showToast(error)
    }

    override fun showNewsList(newsList: List<NewsUM>) {
        hideProgressBar()
        if (shouldRefreshNews) {
            newsAdapter!!.clear()
            endlessScrollListener.resetState()
            shouldRefreshNews = false
            swipeRefreshLayout!!.isRefreshing = false
        }
        newsAdapter!!.addNewsList(newsList)

        if (newsAdapter?.itemCount == 0) {
            swipeRefreshLayout?.visibility = View.GONE
            textViewEmpty?.isVisible == true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news_list, container, false)
            prepareViews(rootView!!)
            showProgressBar()
            presenter.subscribe(this)
            presenter.loadNewsList()
        }

        return rootView
    }

    private fun prepareViews(rootView: View) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout!!.setOnRefreshListener({ refreshData() })

        recyclerView = rootView.findViewById(R.id.recycler_view_news)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = linearLayoutManager
        if (context != null && isAdded) {
            newsAdapter = NewsRecyclerViewAdapter(requireContext(), this)
        }
        endlessScrollListener = object : EndlessScroll(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.loanNextNewsPart()
            }
        }
        recyclerView!!.addOnScrollListener(endlessScrollListener)
        recyclerView!!.adapter = newsAdapter
        textViewEmpty = rootView.findViewById(R.id.text_view_news_empty)
        rootView.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.open_settings) {
                presenter.openSettings()
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    fun refreshData() {
        shouldRefreshNews = true
        presenter.reloadData()
    }

    override fun hideProgressBar() {
        progress_bar?.visibility = View.GONE
        swipeRefreshLayout?.isRefreshing = false
        swipeRefreshLayout?.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        if (newsAdapter!!.itemCount == 0) {
            progress_bar?.bringToFront()
            progress_bar?.visibility = View.VISIBLE
            swipeRefreshLayout?.isRefreshing = true
        }
    }

    override fun onNewsClicked(news: NewsUM) {
        presenter.openNewsDetails(news)
    }
}
