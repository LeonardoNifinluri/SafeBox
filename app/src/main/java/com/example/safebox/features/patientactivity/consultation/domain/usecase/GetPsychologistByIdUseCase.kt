package com.example.safebox.features.patientactivity.consultation.domain.usecase

import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.domain.repository.PsychologistRepository

class GetPsychologistByIdUseCase(private val repository: PsychologistRepository) {
    suspend operator fun invoke(userId: String): Psychologist?{
        return repository.getPsychologistById(userId = userId)
    }
}