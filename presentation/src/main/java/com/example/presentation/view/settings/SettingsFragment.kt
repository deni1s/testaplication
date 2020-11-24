package com.example.presentation.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.entity.LinkUM
import com.example.presentation.utils.fragment.BaseFragment
import com.example.presentation.utils.fragment.hideKeyboard
import com.example.presentation.utils.fragment.showToast
import com.example.presentation.view.newslist.NewsListViewModel
import com.example.presentation.view.settings.recyclerview.LinkClickCallback
import com.example.presentation.view.settings.recyclerview.LinksRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_settings.view.*
import javax.inject.Inject

class SettingsFragment : BaseFragment(), LinkClickCallback {

    private lateinit var presenter: SettingsViewModel
    private var linksAdapter: LinksRecyclerViewAdapter? = null
    private var addLinkLabel: TextView? = null
    private var linkTemplateRecyclerView: RecyclerView? = null

    private fun showLinkList(link: List<LinkUM>) {
        linksAdapter?.addLinkList(link)
        addLinkLabel?.isVisible = link.isNotEmpty()
        linkTemplateRecyclerView?.isVisible = link.isNotEmpty()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ViewModelProviders.of(this, modelFactory)[SettingsViewModel::class.java]
        presenter.fetchLinkList()
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun bindSubscriptions() {
        presenter.setShowLinkListListener(this::showLinkList)
        presenter.setShowToastListener(this::showToast)
        presenter.setPopBackStackListener(this::popBackStack)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews(view)
    }

    private fun prepareViews(rootView: View) {
        val editTextLink = rootView.findViewById<EditText>(R.id.edit_text_link)
        val buttonAddLink = rootView.findViewById<Button>(R.id.button_add_link)
        buttonAddLink.setOnClickListener {
            val url = editTextLink.text.toString().trim()
            presenter.saveUrl(url)
        }
        rootView.settings_toolbar.setNavigationOnClickListener { popBackStack() }
        addLinkLabel = rootView.findViewById(R.id.settings_add_link_label)
        prepareRecyclerView(rootView.settings_template_links_view)
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }

    private fun prepareRecyclerView(recyclerView: RecyclerView) {
        linkTemplateRecyclerView = recyclerView
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager
        linksAdapter = LinksRecyclerViewAdapter(this)
        recyclerView.adapter = linksAdapter
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

    override fun onLinkClicked(link: LinkUM) {
        presenter.saveLink(link)
    }
}
