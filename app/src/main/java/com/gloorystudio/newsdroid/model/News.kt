package com.gloorystudio.newsdroid.model

import android.os.Parcelable
import com.gloorystudio.newsdroid.news.BigNewFragment
import com.gloorystudio.newsdroid.news.MidNewFragment
import com.gloorystudio.newsdroid.news.SmallNewFragment
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val detail: String,
    val img: Int,
    val topic: String,
    val size: NewsSize
): Parcelable

enum class NewsSize {
    BIG,
    MID,
    SMALL;
}