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
    private val _diariesState = MutableStateFlow<Result<List<Diary>>>(Result.Loading)
    val diariesState: StateFlow<Result<List<Diary>>> = _diariesState

    fun fetchAllDiary(userId: String){
        viewModelScope.launch {
            try{
                val getAllDiaryUseCase = GetAllDiaryUseCase(DiaryRepositoryImpl())
                val diaries = getAllDiaryUseCase(userId = userId)
                if(diaries.isNotEmpty()){
                    _diariesState.value = Result.Success(diaries)
                    Log.d("GetDiaryStatus", "Success")
                }else{
                    _diariesState.value = Result.Empty
                    Log.d("GetDiaryStatus", "Diary is empty")
                }
            }catch (e: Exception){
                _diariesState.value = Result.Error(exception = e)
                Log.e("GetDiaryStatus", "Error when get all diary: ${e.message}")
            }
        }

    }
}