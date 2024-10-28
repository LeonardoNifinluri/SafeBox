package com.example.safebox.features.auth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.auth.data.repository.AuthRepository
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.auth.domain.model.SignUpData
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel: ViewModel() {

    private val authRepository = AuthRepository()
    var signUpData by mutableStateOf(SignUpData())
        private set
    var isLoading by mutableStateOf(false)
        private set

    var result by mutableStateOf<AuthResult?>(value = null)
        private set

    fun onEmailChange(newEmail: String){
        signUpData = signUpData.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String){
        signUpData = signUpData.copy(password = newPassword)
    }

    fun onRoleChange(newRole: Role){
        signUpData = signUpData.copy(role = newRole)
    }

    fun onSubmit(onSignUpSuccess: () -> Unit){
        isLoading = true
        viewModelScope.launch {
            try{
                result = authRepository.signUp(signUpData)
                if(result != null){
                    onSignUpSuccess()
                }
            }finally{
                isLoading = false
                delay(timeMillis = 3000)
            }
        }
    }
}