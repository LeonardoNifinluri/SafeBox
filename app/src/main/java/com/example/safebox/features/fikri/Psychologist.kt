package com.example.safebox.features.fikri

import androidx.annotation.DrawableRes

data class Psychologist(
    val name: String,
    val specialization: String,
    @DrawableRes val imageRes: Int
)
