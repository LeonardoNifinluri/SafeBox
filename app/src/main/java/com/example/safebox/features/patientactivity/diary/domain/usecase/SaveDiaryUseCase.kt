package com.example.safebox.features.patientactivity.diary.domain.usecase

import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.repository.DiaryRepository

class SaveDiaryUseCase(private val repository: DiaryRepository) {
    suspend operator fun invoke(userId: String, diary: Diary): Boolean{
        return repository.createDiary(
            userId = userId,
            diary = diary
        )
    }
}