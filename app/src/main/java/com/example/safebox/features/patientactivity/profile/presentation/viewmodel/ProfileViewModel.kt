package com.example.safebox.features.patientactivity.profile.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.core.result.Result
import com.example.safebox.features.auth.data.repository.AuthRepository
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userId: String,
    private val getPatientDataUseCase: GetPatientDataUseCase
): ViewModel() {

    // StateFlow for managing the state of the fetched Patient data
    private val _patientState = MutableStateFlow<Result<Patient?>>(Result.Loading)
    val patientState: StateFlow<Result<Patient?>> = _patientState

    private val firebaseAuth = AuthRepository()
    fun signOut(onSuccess: () -> Unit){
        firebaseAuth.signOut {
            onSuccess()
        }
    }

    fun onRefresh(){
        fetchPatientData()
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
}