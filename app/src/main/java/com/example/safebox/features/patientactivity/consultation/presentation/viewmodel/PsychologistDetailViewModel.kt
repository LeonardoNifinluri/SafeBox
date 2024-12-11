package com.example.safebox.features.patientactivity.consultation.presentation.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.data.PsychologistRepositoryImpl
import com.example.safebox.features.patientactivity.consultation.domain.usecase.GetPsychologistByIdUseCase
import com.example.safebox.features.patientactivity.history.data.repository.HistoryRepositoryImpl
import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.domain.usecase.CreateHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PsychologistDetailViewModel: ViewModel() {

    private val _psychologistState = MutableStateFlow<Result<Psychologist>>(Result.Loading)
    val psychologistState: StateFlow<Result<Psychologist>> = _psychologistState

    private val _selectedDay = mutableIntStateOf(value = -1)
    val selectedDay: State<Int> = _selectedDay

    private val _showModal = mutableStateOf(value = false)
    val showModal: State<Boolean> = _showModal

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val createHistoryUseCase = CreateHistoryUseCase(HistoryRepositoryImpl())

    fun onChooseDay(){
        _showModal.value = true
    }
    fun onCancelChooseDay(){
        _showModal.value = false
    }
    fun onChangeSelectedDay(newDay: Int){
        _selectedDay.intValue = newDay
    }
    fun createHistory(
        userId: String,
        history: History,
        onSuccess: () -> Unit,
        onFail: () -> Unit
    ){
        _isLoading.value = true
        viewModelScope.launch {
            val isCreated = createHistoryUseCase(
                userId = userId,
                history = history
            )
            if(isCreated){
                onSuccess()
            }else{
                onFail()
            }
            _isLoading.value = false
        }
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
    fun getCurrentDateTime(): String {
        val currentDateTime = LocalDateTime.now() // Get the current date and time
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") // Specify the format
        return currentDateTime.format(formatter) // Format and return as a string
    }
}