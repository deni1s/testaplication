package com.example.presentation.routing

import android.os.Bundle
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.entity.NewsUM

class NewsNavigatorImpl(val navController: NavController) : NewsNavigator {
    override fun newsDetailsRequired(news: NewsUM) {
        val bundle = Bundle().apply {
            putParcelable(NEWS_PARAM, news)
        }
        navController.navigate(R.id.newsDetailFragment, bundle)
    }

    override fun settingsRequired() {
        navController.navigate(R.id.settingsFragment)
    }

    override fun backIntent() {
        navController.popBackStack()
    }
}

