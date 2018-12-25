package com.example.denis.myapplication.MainActivity.fragments.news

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.example.denis.myapplication.MainActivity.adapters.NewsRecyclerViewAdapter
import com.example.denis.myapplication.MainActivity.fragments.BaseFragment
import com.example.denis.myapplication.MainActivity.fragments.news.newsdetail.NewsDetailFragment
import com.example.denis.myapplication.MainActivity.fragments.settings.SettingsFragment
import com.example.denis.myapplication.model.classes.News
import com.example.denis.myapplication.model.repositories.realm.NewsRepository
import com.example.denis.myapplication.model.recyclerview.EndlessScroll
import com.example.denis.myapplication.R
import com.example.denis.myapplication.model.repositories.realm.LinksRepository
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class NewsFragment : BaseFragment(), NewsListContract.View, NewsClickCallback {

    private var recyclerView: RecyclerView? = null
    private var textViewEmpty: TextView? = null
    private var newsAdapter: NewsRecyclerViewAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var rootView: View? = null
    var shouldRefreshNews: Boolean = false
    lateinit var endlessScrollListener: EndlessScroll

    private val newsRepository: NewsRepository by inject()
    private val linksRepository: LinksRepository by inject()
    override val presenter: NewsListContract.Presenter by inject { parametersOf(linksRepository.getLinkList())}


    override fun showError(error: String) {
        hideProgressBar()
        showToast(error)
    }

    override fun showNewsList(newsList: List<News>) {
        hideProgressBar()
        if (shouldRefreshNews) {
            newsAdapter!!.clear();
            endlessScrollListener.resetState();
            shouldRefreshNews = false;
            swipeRefreshLayout!!.setRefreshing(false)
        }
        markWatchedNews(newsList)
        newsAdapter!!.addNewsList(newsList)

        if (newsAdapter!!.itemCount == 0) {
            swipeRefreshLayout!!.visibility = View.GONE
            textViewEmpty!!.visibility == View.VISIBLE
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news_list, container, false)
            prepareViews(rootView!!)
            showProgressBar()
            presenter.subscribe(this)
            presenter.loadNewsList()
        }
        setTitle(getString(R.string.title_news))
        setHasOptionsMenu(true)

        return rootView
    }

    private fun prepareViews(rootView: View) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout!!.setOnRefreshListener({ refreshData() })

        recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_news)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = linearLayoutManager
        if (context != null && isAdded) {
            newsAdapter = NewsRecyclerViewAdapter(context!!, this)
        }
        endlessScrollListener = object : EndlessScroll(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.loadNewsList()
            }
        }
        recyclerView!!.addOnScrollListener(endlessScrollListener)
        recyclerView!!.adapter = newsAdapter
        textViewEmpty = rootView.findViewById(R.id.text_view_news_empty)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_settings -> openFragment(SettingsFragment.newInstance(), true)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    fun refreshData() {
        shouldRefreshNews = true
        presenter.reloadData()
    }

    fun markWatchedNews(list: List<News>) {
        list.forEach({
            if (newsRepository.isNewsWasWatched(it)) {
                it.isNewsWatched = true
            }
        })
    }

    override fun hideProgressBar() {
        super.hideProgressBar()
        swipeRefreshLayout!!.setRefreshing(false)
        swipeRefreshLayout!!.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        if (newsAdapter!!.itemCount == 0) {
            super.showProgressBar()
            swipeRefreshLayout!!.setRefreshing(true)
        }
    }

    override fun onNewsClicked(news: News) {
        news.isNewsWatched = true
        newsRepository.addNewsItemRead(news);
        openFragment(NewsDetailFragment.newInstance(news), true)
    }

    companion object {

        val FRAGMENT_TAG = "newsFragment"

        fun newInstance(): NewsFragment {
            return NewsFragment()
        }

    }
}
