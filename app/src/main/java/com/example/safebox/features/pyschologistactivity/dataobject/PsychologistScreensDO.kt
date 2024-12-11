package com.example.safebox.features.pyschologistactivity.dataobject

sealed class PsychologistScreensDO(val screen: String) {
    data object Home: PsychologistScreensDO(screen = "home")
}