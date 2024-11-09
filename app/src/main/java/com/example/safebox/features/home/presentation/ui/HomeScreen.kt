package com.example.safebox.features.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.safebox.features.auth.domain.model.Role

@Composable
fun HomeScreen(navController: NavController, userId: String, userRole: String) {

    Box{
        Column {
            Text(text = userId)

            Spacer(modifier = Modifier.height(30.dp))

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