package com.example.safebox.features.auth.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.auth.data.repository.AuthRepository
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.auth.domain.model.SignInData
import com.example.safebox.features.fillprofile.data.repository.FirebaseRepository
import com.example.safebox.features.fillprofile.domain.usecase.GetUserRoleUseCase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private val getUserRoleUseCase = GetUserRoleUseCase(FirebaseRepository())

    private val authRepository = AuthRepository()

    private val _signInData = mutableStateOf(SignInData())
    val signInData: State<SignInData> = _signInData

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val _result = mutableStateOf<AuthResult?>(value = null)
    val result: State<AuthResult?> = _result

//    private val _role = MutableLiveData<Role>()
//    val role: LiveData<Role> get() = _role

    private val _role = mutableStateOf(Role.UNKNOWN)
    val role: State<Role> = _role

    private val _message = mutableStateOf<String?>(value = null)
    val message: State<String?> = _message

    fun onEmailChange(newEmail: String){
        _signInData.value = _signInData.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPass: String){
        _signInData.value = _signInData.value.copy(password = newPass)
    }

    fun onSubmit(onSignInSuccess: () -> Unit){
        _isLoading.value = true
        viewModelScope.launch{
            try{
                _result.value = authRepository.signIn(_signInData.value)
                val userId = result.value?.user?.uid ?: ""
                _role.value = getUserRoleUseCase(userId = userId)
                onSignInSuccess()
            }catch(e: FirebaseAuthException){
                _message.value = "Firebase sign-in error: ${e.message}"
            } catch(e: Exception){
                _message.value = "Unexpected error during sign-in: ${e.message}"
            } finally {
                _isLoading.value = false
                delay(timeMillis = 3000)
                _message.value = null
            }
        }
    }

}