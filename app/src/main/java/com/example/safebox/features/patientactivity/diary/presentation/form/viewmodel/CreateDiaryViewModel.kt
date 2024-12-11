package com.example.safebox.features.patientactivity.diary.presentation.form.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.patientactivity.diary.data.DiaryRepositoryImpl
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.usecase.SaveDiaryUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateDiaryViewModel: ViewModel() {

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val _diary = mutableStateOf(value = Diary())
    val diary: State<Diary> = _diary

    private val saveDiaryUseCase = SaveDiaryUseCase(DiaryRepositoryImpl())

    fun createDiary(
        userId: String,
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ){
        _isLoading.value = true
        viewModelScope.launch {
            _diary.value = _diary.value.copy(createdAt = getCurrentDateTime())
            val isDiaryCreated = saveDiaryUseCase(userId, diary.value)
            if(isDiaryCreated){
                onSuccess()
            }else{
                onFail()
            }
            _isLoading.value = false
        }
    }

    fun onTitleChange(newTitle: String){
        _diary.value = _diary.value.copy(title = newTitle)
    }

    fun onContentChange(newContent: String){
        _diary.value = _diary.value.copy(content = newContent)
    }

    private fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now() // Get the current date and time
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") // Specify the format
        return currentDateTime.format(formatter) // Format and return as a string
    }

}