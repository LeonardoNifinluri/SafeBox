package com.example.safebox.features.auth.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _signUpData = mutableStateOf(value = SignUpData())
    val signUpData: State<SignUpData> = _signUpData

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val _result = mutableStateOf<AuthResult?>(value = null)
    val result: State<AuthResult?> = _result

    private val _message = mutableStateOf<String?>(value = null)
    val message: State<String?> = _message

    fun onEmailChange(newEmail: String){
        _signUpData.value = _signUpData.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String){
        _signUpData.value = _signUpData.value.copy(password = newPassword)
    }

    fun onRoleChange(newRole: Role){
        _signUpData.value = _signUpData.value.copy(role = newRole)
    }

    fun onSubmit(onSignUpSuccess: () -> Unit){
        _isLoading.value = true
        viewModelScope.launch {
            try{
                _result.value = authRepository.signUp(_signUpData.value)
                if(_result.value != null){
                    onSignUpSuccess()
                }else{
                    _message.value = "Error when SignUp"
                }
            }finally{
                _isLoading.value = false
                delay(timeMillis = 3000)
                _message.value = null
            }
        }
    }
}