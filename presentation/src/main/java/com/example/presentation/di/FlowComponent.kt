package com.example.presentation.di

import androidx.navigation.NavController
import com.example.presentation.view.newsdetails.NewsDetailViewModel
import com.example.presentation.view.newslist.NewsListViewModel
import com.example.presentation.view.settings.SettingsViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent
interface FlowComponent {
  fun newsDetailsViewModel() : Provider<NewsDetailViewModel>
  fun newsListViewModel() : Provider<NewsListViewModel>
  fun settingsViewModel() : Provider<SettingsViewModel>

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    fun navController(navController: NavController): Builder
    fun build(): FlowComponent
  }
}
