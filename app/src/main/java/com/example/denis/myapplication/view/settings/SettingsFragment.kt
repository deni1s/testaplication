package com.example.denis.myapplication.view.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.denis.myapplication.view.BaseFragment
import com.example.denis.myapplication.view.newslist.NewsFragment
import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.R
import com.example.denis.myapplication.repositories.realm.LinksRealmRepository
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(), SettingsContract.View {


    override val presenter: SettingsContract.Presenter by inject()
    private val linkRepository: LinksRealmRepository by inject()


    override fun showError(error: String) {
        showToast(error)
    }

    fun linkWasAddedBefore() {
        showToast(context!!.getString(R.string.alert_link_already_saved))
    }

    override fun linkIsValid(link: Link) {
        linkRepository.addLinkToDataBase(link)
        showToast(getString(R.string.alert_success_link_adding))
        val fragment =
            activity!!.getSupportFragmentManager().findFragmentByTag(NewsFragment.FRAGMENT_TAG) as NewsFragment
        fragment.refreshData()
        popBackStack()
    }

    override fun linkNotValid() {
        showToast(context!!.getString(R.string.alert_wrong_url))
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
                if (!linkRepository.isLinkAlreadySaved(url)) {
                    presenter.checkUrl(url)
                } else {
                    linkWasAddedBefore()
                }
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
