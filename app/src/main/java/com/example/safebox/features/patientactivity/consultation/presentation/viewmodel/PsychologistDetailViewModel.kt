package com.example.safebox.features.patientactivity.consultation.presentation.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.data.PsychologistRepositoryImpl
import com.example.safebox.features.patientactivity.consultation.domain.usecase.GetPsychologistByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PsychologistDetailViewModel: ViewModel() {

    private val _psychologistState = MutableStateFlow<Result<Psychologist>>(Result.Loading)
    val psychologistState: StateFlow<Result<Psychologist>> = _psychologistState

    private val _selectedDay = mutableStateOf(value = "")
    val selectedDay: State<String> = _selectedDay

    private val _showModal = mutableStateOf(value = false)
    val showModal: State<Boolean> = _showModal

    fun onChooseDay(){
        _showModal.value = true
    }
    fun onCancelChooseDay(){
        _showModal.value = false
    }
    fun onChangeSelectedDay(newDay: String){
        _selectedDay.value = newDay
    }
    fun fetchPsychologistById(userId: String){
        viewModelScope.launch {
            try{
                val getPsychologistByIdUseCase = GetPsychologistByIdUseCase(PsychologistRepositoryImpl())
                val psychologist = getPsychologistByIdUseCase(userId = userId)
                if(psychologist != null){
                    _psychologistState.value = Result.Success(psychologist)
                }else{
                    _psychologistState.value = Result.Empty
                }
            }catch (e: Exception){
                _psychologistState.value = Result.Error(exception = e)
            }
        }

    }
}