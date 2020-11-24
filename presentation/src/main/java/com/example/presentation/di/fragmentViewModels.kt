package com.example.presentation.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST") // contract is enforced in code
inline fun <reified T : ViewModel> Fragment.fragmentViewModels(crossinline creator: () -> T): Lazy<T> {
  return createViewModelLazy(
    T::class,
    storeProducer = { viewModelStore },
    factoryProducer = {
      object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
          return creator.invoke() as T
        }
      }
    }
  )
}
