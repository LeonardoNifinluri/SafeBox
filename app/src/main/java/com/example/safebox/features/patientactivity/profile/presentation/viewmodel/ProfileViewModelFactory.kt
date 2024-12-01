package com.example.safebox.features.patientactivity.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase

class ProfileViewModelFactory(
    private val userId: String,
    private val getPatientDataUseCase: GetPatientDataUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(userId, getPatientDataUseCase) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}