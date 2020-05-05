package com.example.presentation.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import com.example.presentation.R
import com.example.presentation.entity.LinkUM
import com.example.presentation.utils.fragment.hideKeyboard
import com.example.presentation.utils.fragment.showToast
import com.example.presentation.view.settings.recyclerview.LinkClickCallback
import com.example.presentation.view.settings.recyclerview.LinksRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment(), SettingsContract.View, LinkClickCallback {

    override val presenter: SettingsContract.Presenter by inject()
    private var linksAdapter: LinksRecyclerViewAdapter? = null
    private var addLinkLabel: TextView? = null
    private var linkTemplateRecyclerView: RecyclerView? = null

    override fun showError(error: String) {
        showToast(error)
    }

    override fun linkWasAddedBefore() {
        showToast(requireContext().getString(R.string.alert_link_already_saved))
    }

    override fun linkSaved() {
        showToast(getString(R.string.alert_success_link_adding))
    }

    override fun showLinkList(link: List<LinkUM>) {
        linksAdapter?.addLinkList(link)
        addLinkLabel?.isVisible = link.isNotEmpty()
        linkTemplateRecyclerView?.isVisible = link.isNotEmpty()
    }

    override fun linkNotValid() {
        showToast(requireContext().getString(R.string.alert_wrong_url))
    }

    override fun linkNotSupported() {
        showToast(requireContext().getString(R.string.link_not_supported))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe(this)
        prepareViews(view)
    }

    private fun prepareViews(rootView: View) {
        val editTextLink = rootView.findViewById<EditText>(R.id.edit_text_link)
        val buttonAddLink = rootView.findViewById<Button>(R.id.button_add_link)
        buttonAddLink.setOnClickListener {
            val url = editTextLink.text.toString().trim()
            presenter.saveUrl(url)
        }
        rootView.settings_toolbar.setNavigationOnClickListener { presenter.popBackStack() }
        addLinkLabel = rootView.findViewById(R.id.settings_add_link_label)
        prepareRecyclerView(rootView.settings_template_links_view)
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
        presenter.unSubscribe()
        super.onDestroy()
    }

    override fun onLinkClicked(link: LinkUM) {
        presenter.saveLink(link)
    }
}
