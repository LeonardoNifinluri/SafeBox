package com.example.safebox.features.fillprofile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safebox.features.fillprofile.domain.usecase.SavePsychologistDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase

class PsychologistViewModelFactory(
    private val userId: String,
    private val email: String,
    private val uploadImageUseCase: UploadImageUseCase,
    private val savePsychologistDataUseCase: SavePsychologistDataUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PsychologistViewModel::class.java)){
            return PsychologistViewModel(userId, email, uploadImageUseCase, savePsychologistDataUseCase) as T
        }

        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}