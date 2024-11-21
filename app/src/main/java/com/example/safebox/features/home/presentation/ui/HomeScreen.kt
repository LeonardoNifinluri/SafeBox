package com.example.safebox.features.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.safebox.features.auth.domain.model.Role

@Composable
fun HomeScreen(navController: NavController, userId: String, userRole: String) {

    Box{
        Column {
            when(Role.valueOf(userRole)){
                Role.PATIENT -> PatientHomeScreen(
                    navController = navController,
                    userId = userId
                )
                Role.PSYCHOLOGIST -> PsychologistHomeScreen(
                    navController = navController,
                    userId = userId
                )
                else -> UnknownUserScreen(
                    navController = navController,
                    userId = userId
                )
            }

        }
    }
}