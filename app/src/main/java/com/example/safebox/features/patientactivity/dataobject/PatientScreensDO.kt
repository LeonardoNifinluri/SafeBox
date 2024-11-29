package com.example.safebox.features.patientactivity.dataobject

sealed class PatientScreensDO(val screen: String) {
    data object Home: PatientScreensDO(screen = "home")
    data object Note: PatientScreensDO(screen = "notes")
    data object History: PatientScreensDO(screen = "history")
    data object Profile: PatientScreensDO(screen = "profile")
}