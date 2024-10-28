package com.example.safebox.features.fillprofile.domain.usecase

import com.example.safebox.features.fillprofile.data.repository.DataRepository
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist

class SavePsychologistDataUseCase(private val repository: DataRepository) {

    suspend operator fun invoke(userId: String, psychologistData: Psychologist): Boolean{
        return repository.savePsychologistData(userId = userId, psychologistData = psychologistData)
    }

}