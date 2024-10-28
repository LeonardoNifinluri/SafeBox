package com.example.safebox.features.auth.data.repository

import com.example.safebox.features.auth.domain.model.SignInData
import com.example.safebox.features.auth.domain.model.SignUpData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository{
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signIn(signInData: SignInData): AuthResult?{
        return try{
            firebaseAuth.signInWithEmailAndPassword(
                signInData.email,
                signInData.password
            ).await()
        }catch (e: Exception){
            null
        }
    }

    suspend fun signUp(signUpData: SignUpData): AuthResult?{
        return try{
            firebaseAuth.createUserWithEmailAndPassword(
                signUpData.email,
                signUpData.password
            ).await()
        }catch (e: Exception){
            null
        }
    }




}