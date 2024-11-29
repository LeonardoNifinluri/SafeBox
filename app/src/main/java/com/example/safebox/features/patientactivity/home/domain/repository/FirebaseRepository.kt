package com.example.safebox.features.patientactivity.home.domain.repository

import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist

interface FirebaseRepository {
    //fetch psychologist data
    suspend fun getPsychologistData(): List<Psychologist>
    //fetch patient data
    suspend fun getPatientData(userId: String): Patient?
}