package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)
val pages = listOf(
    Page(
        "Stay Informed",
        "Get a curated selection of the latest news tailored to your interests.",
        image = R.drawable.onboarding1
    ),
    Page(
        "Personalize Your Feed",
        "Customize your news feed by selecting topics you're passionate about.",
        image = R.drawable.onboarding2
    ),
    Page(
        "Breaking News Alerts",
        "Never miss a beat with instant notifications for breaking news stories.",
        image = R.drawable.onboarding3
    )
)
