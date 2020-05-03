package com.example.presentation.view.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.presentation.view.BaseFragment
import com.example.entity.Link
import com.example.presentation.view.newslist.NewsFragment
import org.koin.android.ext.android.inject
import com.example.presentation.R
import kotlinx.coroutines.GlobalScope
import org.koin.core.parameter.parametersOf

class SettingsFragment : BaseFragment(), SettingsContract.View {

    override val presenter: SettingsContract.Presenter by inject{ parametersOf(GlobalScope) }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun linkWasAddedBefore() {
        showToast(context!!.getString(R.string.alert_link_already_saved))
    }

    override fun linkSaved() {
        showToast(getString(R.string.alert_success_link_adding))
        val fragment =
            activity!!.supportFragmentManager.findFragmentByTag(NewsFragment.FRAGMENT_TAG) as NewsFragment
        fragment.refreshData()
        popBackStack()
    }

    override fun linkNotValid() {
        showToast(context!!.getString(R.string.alert_wrong_url))
    }

    override fun linkNotSupported() {
        showToast(context!!.getString(R.string.link_not_supported))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe(this)
        prepareViews(view)
    }

    fun prepareViews(rootView: View) {
        val editTextLink = rootView.findViewById<EditText>(R.id.edit_text_link)
        setFocusOnEditText(editTextLink)
        val buttonAddLink = rootView.findViewById<Button>(R.id.button_add_link)
        buttonAddLink.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val url = editTextLink.text.toString().trim()
                presenter.saveUrl(url)
            }
        })
    }

    override fun onDestroy() {
        hideKeyboard()
        presenter.unSubscribe()
        super.onDestroy()
    }

    companion object {

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}
