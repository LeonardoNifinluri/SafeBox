package com.example.safebox.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.safebox.features.auth.data.repository.AuthRepository

class PsychologistHomeViewModel: ViewModel() {

    private val firebaseAuth = AuthRepository()

    fun onSignOut(signOutSuccess: () -> Unit){
        firebaseAuth.signOut{
            signOutSuccess()
        }
    }

}