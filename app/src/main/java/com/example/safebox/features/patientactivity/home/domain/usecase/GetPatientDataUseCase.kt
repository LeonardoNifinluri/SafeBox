package com.example.safebox.features.patientactivity.home.domain.usecase

import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.patientactivity.home.domain.repository.FirebaseRepository

class GetPatientDataUseCase(private val repository: FirebaseRepository) {
    suspend operator fun invoke(userId: String): Patient? {
        return repository.getPatientData(userId = userId)
    }
}