package com.example.safebox.features.patientactivity.consultation.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConsultationViewModel(private val getPsychologistDataUseCase: GetPsychologistDataUseCase): ViewModel() {
    private val _psychologistState = MutableStateFlow<Result<List<Psychologist>>>(Result.Loading)
    val psychologistState: StateFlow<Result<List<Psychologist>>> = _psychologistState

    fun onRefresh(){
        fetchPsychologistsData()
    }

    private fun fetchPsychologistsData(){
        viewModelScope.launch {
            try{
                val psychologists = getPsychologistDataUseCase()
                if(psychologists.isNotEmpty()){
                    _psychologistState.value = Result.Success(psychologists)
                }else{
                    _psychologistState.value = Result.Empty
                    Log.d("FetchingPsy", "Not found psychologist data")
                }
            }catch (e: Exception){
                Log.e("ErrorFetch", "Error when fetch psy data: ", e)
                _psychologistState.value = Result.Error(exception = e)
            }
        }
    }
}