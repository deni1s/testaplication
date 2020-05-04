package com.example.presentation.view

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun hideKeyboard() {
        try {
            if (activity != null && requireActivity().currentFocus != null) {
                val inputMethodManager =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
            }
        } catch (e: Exception) {
            Log.e("hideKeyboardException", e.toString())
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
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }, 300)
    }
}
