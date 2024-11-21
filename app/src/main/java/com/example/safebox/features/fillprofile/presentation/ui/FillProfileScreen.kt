package com.example.safebox.features.fillprofile.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.safebox.features.auth.domain.model.Role

@Composable
fun FillProfileScreen(
    navController: NavController,
    userId: String,
    userRole: String,
    userEmail: String
) {

    val role = Role.valueOf(userRole)
    if(role == Role.PATIENT){
        PatientEditProfileScreen(
            navController = navController,
            userId = userId,
            email = userEmail
        )
    }else{
        PsychologistFillProfileScreen(
            navController = navController,
            userId = userId,
            email = userEmail
        )
    }

}