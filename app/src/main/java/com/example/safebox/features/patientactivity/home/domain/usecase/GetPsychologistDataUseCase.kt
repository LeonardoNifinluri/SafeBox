package com.example.safebox.features.patientactivity.home.domain.usecase

import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.home.domain.repository.FirebaseRepository

class GetPsychologistDataUseCase(private val repository: FirebaseRepository) {
    suspend operator fun invoke(): List<Psychologist>{
        return repository.getPsychologistData()
    }
}