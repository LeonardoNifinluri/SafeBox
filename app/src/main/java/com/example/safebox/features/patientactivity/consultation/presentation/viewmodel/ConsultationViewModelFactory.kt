package com.example.safebox.features.patientactivity.consultation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase
import com.example.safebox.features.patientactivity.home.presentation.viewmodel.PatientHomeViewModel

class ConsultationViewModelFactory(
    private val getPsychologistDataUseCase: GetPsychologistDataUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ConsultationViewModel::class.java)){
            return ConsultationViewModel(getPsychologistDataUseCase) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}