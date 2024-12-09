package com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.diary.data.DiaryRepositoryImpl
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.usecase.GetDiaryByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiaryDetailScreenViewModel: ViewModel() {

    private val _diaryState = MutableStateFlow<Result<Diary>>(Result.Loading)
    val diaryState: StateFlow<Result<Diary>> = _diaryState

    fun fetchDiary(
        userId: String,
        diaryId: String
    ){
        viewModelScope.launch {
            try{
                val getDiaryByIdUseCase = GetDiaryByIdUseCase(DiaryRepositoryImpl())
                val diary = getDiaryByIdUseCase(
                    userId = userId,
                    diaryId = diaryId
                )
                _diaryState.value = if(diary != null) Result.Success(diary) else Result.Empty
            }catch (e: Exception){
                _diaryState.value = Result.Error(exception = e)
            }
        }
    }

}