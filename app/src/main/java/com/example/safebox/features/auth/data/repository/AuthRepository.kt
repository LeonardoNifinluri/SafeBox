package com.example.safebox.features.auth.data.repository

import android.util.Log
import com.example.safebox.features.auth.domain.model.SignInData
import com.example.safebox.features.auth.domain.model.SignUpData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class AuthRepository{
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signIn(signInData: SignInData): AuthResult?{
        return try{
            firebaseAuth.signInWithEmailAndPassword(
                signInData.email,
                signInData.password
            ).await()
        }catch (e: FirebaseAuthException){
            Log.e("SignInError", "FirebaseAuth Error: " + e.errorCode, e)
            throw e
        }catch (e: Exception){
            Log.e("SignInError", e.message ?: "Error when SignIn", e)
            throw e
        }
    }

    suspend fun signUp(signUpData: SignUpData): AuthResult?{
        return try{
            firebaseAuth.createUserWithEmailAndPassword(
                signUpData.email,
                signUpData.password
            ).await()
        }catch (e: FirebaseAuthException){
            Log.e("SignUpError", "FirebaseAuth Error: " + e.errorCode, e)
            throw e
        }catch (e: Exception){
            Log.e("SignUpError", e.message ?: "Error when SignUp", e)
            throw e
        }
    }

    fun signOut(signOutSuccess: () -> Unit){
        firebaseAuth.signOut()
        signOutSuccess()
    }
}