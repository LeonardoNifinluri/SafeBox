package com.example.safebox.features.patientactivity.history.domain.repository

import com.example.safebox.features.patientactivity.history.domain.model.History

interface HistoryRepository {

    suspend fun createHistory(userId: String, history: History): Boolean
    suspend fun getHistories(userId: String): List<History>

}