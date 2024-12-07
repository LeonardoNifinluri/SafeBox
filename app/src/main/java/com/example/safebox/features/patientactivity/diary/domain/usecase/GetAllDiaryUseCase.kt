package com.example.safebox.features.patientactivity.diary.domain.usecase

import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.repository.DiaryRepository

class GetAllDiaryUseCase(private val repository: DiaryRepository) {
    suspend operator fun invoke(userId: String): List<Diary>{
        return repository.getAllDiary(userId = userId)
    }
}