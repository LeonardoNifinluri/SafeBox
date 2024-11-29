package com.example.safebox.features.patientactivity.home.domain.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)
