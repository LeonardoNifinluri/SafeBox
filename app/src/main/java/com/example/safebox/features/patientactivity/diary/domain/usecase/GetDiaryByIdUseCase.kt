package com.example.safebox.features.patientactivity.diary.domain.usecase

import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.repository.DiaryRepository

class GetDiaryByIdUseCase(private val repository: DiaryRepository) {
    suspend operator fun invoke(userId: String, diaryId: String): Diary?{
        return repository.getDiaryById(
            userId = userId,
            diaryId = diaryId
        )
    }
}