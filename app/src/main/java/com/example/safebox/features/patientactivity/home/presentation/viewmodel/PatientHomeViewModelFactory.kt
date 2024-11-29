package com.example.safebox.features.patientactivity.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase

class PatientHomeViewModelFactory(
    private val userId: String,
    private val getPatientDataUseCase: GetPatientDataUseCase,
    private val getPsychologistDataUseCase: GetPsychologistDataUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PatientHomeViewModel::class.java)){
            return PatientHomeViewModel(userId, getPatientDataUseCase, getPsychologistDataUseCase) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}