package com.example.safebox.features.patientactivity.history.domain.model

data class History(
    val psychologistId: String,
    val psychologistName: String,
    val day: Int,
    val createdAt: String,
    var id: String = ""
)
