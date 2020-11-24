package com.example.presentation.view.newslist

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.presentation.utils.recyclerview.EndlessScroll
import com.example.presentation.R
import com.example.presentation.entity.NewsUM
import com.example.presentation.utils.fragment.BaseFragment
import com.example.presentation.utils.fragment.showToast
import com.example.presentation.view.newslist.recyclerview.NewsClickCallback
import com.example.presentation.view.newslist.recyclerview.NewsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import kotlinx.android.synthetic.main.progress_bar.*

class NewsListFragment : BaseFragment(), NewsClickCallback {
    private var recyclerView: RecyclerView? = null
    private var textViewEmpty: TextView? = null
    private var newsAdapter: NewsRecyclerViewAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var rootView: View? = null
    private var shouldRefreshNews: Boolean = false
    private lateinit var endlessScrollListener: EndlessScroll
    private lateinit var presenter: NewsListViewModel

    private fun showError(error: String) {
        showToast(error)
    }

    private fun showNewsList(newsList: List<NewsUM>) {
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

    override fun bindSubscriptions() {
        presenter.setShowErrorListener(this::showError)
        presenter.setShowNewsListListener(this::showNewsList)
        presenter.setShowProgressBarListener(this::showProgressBar)
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
            presenter = ViewModelProviders.of(this, modelFactory)[NewsListViewModel::class.java]
            presenter.loadNewsList()
        }

        return rootView
    }

    private fun prepareViews(rootView: View) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout?.setOnRefreshListener { refreshData() }

        recyclerView = rootView.findViewById(R.id.recycler_view_news)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = linearLayoutManager
        if (isAdded) {
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
                val action = NewsListFragmentDirections.actionNewsFragmentToSettingsFragment3()
                findNavController().navigate(action)
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun refreshData() {
        shouldRefreshNews = true
        presenter.reloadData()
    }

    private fun showProgressBar(show: Boolean) {
        progress_bar?.isVisible = show
        swipeRefreshLayout?.isRefreshing = true
    }

    override fun onNewsClicked(news: NewsUM) {
        val action = NewsListFragmentDirections.actionNewsFragmentToNewsDetailFragment(news)
        findNavController().navigate(action)
    }
}
