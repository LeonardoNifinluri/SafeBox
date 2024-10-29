package com.example.safebox.features.auth.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.auth.data.repository.AuthRepository
import com.example.safebox.features.auth.domain.model.SignInData
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private val authRepository = AuthRepository()
    var signInData by mutableStateOf(SignInData())
        private set
    var isLoading by mutableStateOf(false)
        private set

    var result by mutableStateOf<AuthResult?>(value = null)
        private set

    fun onEmailChange(newEmail: String){
        signInData = signInData.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String){
        signInData = signInData.copy(password = newPass)
    }

    fun onSubmit(onSignInSuccess: () -> Unit){
        isLoading = true
        viewModelScope.launch{
            try{
                result = authRepository.signIn(signInData)
                if(result != null){
                    onSignInSuccess()
                }
            }finally {
                isLoading = false
                delay(timeMillis = 3000)
            }
        }
    }

    //this is temporary

}