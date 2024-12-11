package com.example.safebox.features.patientactivity.history.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.history.data.repository.HistoryRepositoryImpl
import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.domain.usecase.GetHistoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryScreenViewModel: ViewModel() {

    private val _historiesState = MutableStateFlow<Result<List<History>>>(Result.Loading)
    val historiesState: StateFlow<Result<List<History>>> = _historiesState

    fun fetchAllHistories(patientId: String){
        viewModelScope.launch {
            try{
                val getHistoriesUseCase = GetHistoriesUseCase(HistoryRepositoryImpl())
                val histories = getHistoriesUseCase(userId = patientId)
                _historiesState.value = if(histories.isNotEmpty()) Result.Success(histories) else Result.Empty
                Log.d("FetchHistories", "Success")
            }catch (e: Exception){
                _historiesState.value = Result.Error(exception = e)
                Log.d("FetchHistories", "Fail with error: ${e.message}")
            }
        }
    }

    fun getDayString(day: Int): String{
        if(day == -1){
            return "Wrong Day"
        }
        val daysOfWeek = listOf(
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumat",
            "Sabtu",
            "Minggu"
        )
        return daysOfWeek[day]
    }

}