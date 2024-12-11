package com.example.safebox.features.patientactivity.history.domain.usecase

import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.domain.repository.HistoryRepository

class CreateHistoryUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(
        userId: String,
        history: History
    ): Boolean{
        return repository.createHistory(
            userId = userId,
            history = history
        )
    }
}