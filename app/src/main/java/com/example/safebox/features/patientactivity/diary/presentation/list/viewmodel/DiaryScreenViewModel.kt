package com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.diary.data.DiaryRepositoryImpl
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.usecase.GetAllDiaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiaryScreenViewModel: ViewModel() {
    private val _diaryState = MutableStateFlow<Result<List<Diary>>>(Result.Loading)
    val diaryState: StateFlow<Result<List<Diary>>> = _diaryState

    fun fetchAllDiary(userId: String){
        viewModelScope.launch {
            try{
                val getAllDiaryUseCase = GetAllDiaryUseCase(DiaryRepositoryImpl())
                val diaries = getAllDiaryUseCase(userId = userId)
                if(diaries.isNotEmpty()){
                    _diaryState.value = Result.Success(diaries)
                    Log.d("GetDiaryStatus", "Success")
                }else{
                    _diaryState.value = Result.Empty
                    Log.d("GetDiaryStatus", "Diary is empty")
                }
            }catch (e: Exception){
                _diaryState.value = Result.Error(exception = e)
                Log.e("GetDiaryStatus", "Error when get all diary: ${e.message}")
            }
        }

    }
}