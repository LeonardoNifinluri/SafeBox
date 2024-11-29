package com.example.safebox.features.patientactivity.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.auth.data.repository.AuthRepository
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase

class PatientHomeViewModel(
    private val userId: String,
    private val getPatientDataUseCase: GetPatientDataUseCase,
    private val getPsychologistDataUseCase: GetPsychologistDataUseCase
): ViewModel(){
    private val firebaseAuth = AuthRepository()

    // StateFlow for managing the state of the fetched Psychologist data
    private val _psychologistState = MutableStateFlow<Result<List<Psychologist>>>(Result.Loading)
    val psychologistState: StateFlow<Result<List<Psychologist>>> = _psychologistState

    // StateFlow for managing the state of the fetched Patient data
    private val _patientState = MutableStateFlow<Result<Patient?>>(Result.Loading)
    val patientState: StateFlow<Result<Patient?>> = _patientState

    fun onRefresh(){
        fetchPsychologistsData()
        fetchPatientData()
        Log.d("CheckPsyData", "Data is: ${psychologistState.value}")
    }
    private fun fetchPsychologistsData(){
        viewModelScope.launch {
            _psychologistState.value = Result.Loading
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
    private fun fetchPatientData(){
        viewModelScope.launch {
            _patientState.value = Result.Loading
            try{
                val patient = getPatientDataUseCase(userId = userId)
                if(patient != null){
                    _patientState.value = Result.Success(patient)
                }else{
                    Log.d("PatientNotFound", "There is no patient with id: $userId")
                    _patientState.value = Result.Empty
                }
            }catch (e: Exception){
                Log.e("ErrorFetch", "Error when fetch pat data: ", e)
                _patientState.value = Result.Error(exception = e)
            }
        }
    }

    fun onSignOut(signOutSuccess: () -> Unit){
        firebaseAuth.signOut {
            signOutSuccess()
        }
    }

}

