package com.example.safebox.features.fillprofile.data.repository

import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist

interface DataRepository {
    suspend fun savePatientData(userId: String, patientData: Patient): Boolean
    suspend fun savePsychologistData(userId: String, psychologistData: Psychologist): Boolean
    suspend fun getUserRole(userId: String): Role
}