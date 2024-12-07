package com.example.safebox.features.patientactivity.consultation.domain.repository

import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist

interface PsychologistRepository {
    suspend fun getPsychologistById(userId: String): Psychologist?
}