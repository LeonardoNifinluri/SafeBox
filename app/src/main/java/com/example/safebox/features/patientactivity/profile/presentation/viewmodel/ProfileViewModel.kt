package com.example.safebox.features.patientactivity.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.safebox.features.auth.data.repository.AuthRepository

class ProfileViewModel: ViewModel() {
    private val firebaseAuth = AuthRepository()
    fun signOut(onSuccess: () -> Unit){
        firebaseAuth.signOut {
            onSuccess()
        }
    }
}