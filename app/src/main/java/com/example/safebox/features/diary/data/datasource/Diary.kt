package com.example.safebox.features.diary.data.datasource

import kotlinx.datetime.LocalDate

data class Diary(
    val patientEmail: String,
    val timeCreated: LocalDate,
    var title: String,
    var content: String
)
