package com.example.safebox.features.fillprofile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safebox.features.fillprofile.domain.usecase.SavePatientDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase

class PatientViewModelFactory(
    private val userId: String,
    private val email: String,
    private val uploadImageUseCase: UploadImageUseCase,
    private val savePatientDataUseCase: SavePatientDataUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PatientViewModel::class.java)){
            return PatientViewModel(userId, email, uploadImageUseCase, savePatientDataUseCase) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}