package com.example.safebox.features.fillprofile.domain.model.patient

import kotlinx.datetime.LocalDate

data class MedicalRecord(
    val id: String,
    val patientEmail: String,
    val doctorEmail: String,
    val visitDate: LocalDate,
    val diagnosis: String,
    val prescribedMedication: String,
    val symptoms: List<String>,
    val treatmentPlan: String,
    val testsConducted: List<String>,
    val testResult: String?,
    val allergies: List<String>?,
    val followUpDate: String?,
    val notes: String?
    )
