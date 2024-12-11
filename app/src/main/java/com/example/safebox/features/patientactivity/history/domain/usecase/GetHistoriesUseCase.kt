package com.example.safebox.features.patientactivity.history.domain.usecase

import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.domain.repository.HistoryRepository

class GetHistoriesUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(userId: String): List<History>{
        return repository.getHistories(userId = userId)
    }
}