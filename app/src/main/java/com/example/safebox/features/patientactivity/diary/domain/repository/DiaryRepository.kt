package com.example.safebox.features.patientactivity.diary.domain.repository

import com.example.safebox.features.patientactivity.diary.domain.model.Diary

interface DiaryRepository {
    suspend fun createDiary(
        userId: String,
        diary: Diary
    ): Boolean
    suspend fun getAllDiary(): Boolean
}