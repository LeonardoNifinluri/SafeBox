package com.example.safebox.features.fikri

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)
