package com.example.presentation.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsUM(
    val description: String?,
    val title: String,
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?,
    val isNewsWatched: Boolean
): Parcelable
