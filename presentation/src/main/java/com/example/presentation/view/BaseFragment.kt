package com.example.presentation.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.presentation.R

open class BaseFragment : Fragment() {

    private var progressBar: View? = null


    protected fun openFragment(fragment: BaseFragment, addToBackStack: Boolean) {
        val supportFragmentManager = activity!!.supportFragmentManager.beginTransaction()
        supportFragmentManager.replace(R.id.fragment_wrapper, fragment)
        if (addToBackStack) {
            supportFragmentManager.addToBackStack(null)
        }
        supportFragmentManager.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (progressBar == null) {
            progressBar = inflater.inflate(R.layout.progress_bar, container, false)
            container!!.addView(progressBar)
        }

        return null
    }

    fun setTitle(title: String) {
        activity!!.setTitle(title)
    }

    open fun hideProgressBar() {
        if (progressBar != null) {
            progressBar!!.setVisibility(View.GONE)
        }
    }

    open fun showProgressBar() {
        if (progressBar != null) {
            progressBar!!.bringToFront()
            progressBar!!.setVisibility(View.VISIBLE)
        }
    }

    protected fun popBackStack() {
        val fragmentActivity = activity ?: return
        val fragmentManager = fragmentActivity.supportFragmentManager ?: return
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

    fun hideKeyboard() {
        try {
            if (activity != null && activity!!.currentFocus != null) {
                val inputMethodManager =
                    activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)
            }
        } catch (e: Exception) {
        }

    }

    protected fun showToast(text: String) {
        val activity = activity
        if (activity != null && isAdded()) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun setFocusOnEditText(editText: EditText) {
        editText.requestFocus()
        editText.postDelayed({
            val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }, 300)
    }
}