package com.example.presentation.utils.fragment

import android.app.Activity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
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

fun Fragment.showToast(text: String) {
    val activity = activity
    if (activity != null && isAdded) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }
}
