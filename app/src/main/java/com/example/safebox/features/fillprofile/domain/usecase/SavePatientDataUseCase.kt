package com.example.safebox.features.fillprofile.domain.usecase

import com.example.safebox.features.fillprofile.data.repository.DataRepository
import com.example.safebox.features.fillprofile.domain.model.patient.Patient

class SavePatientDataUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(userId: String, patientData: Patient): Boolean{
        return repository.savePatientData(userId, patientData)
    }
}