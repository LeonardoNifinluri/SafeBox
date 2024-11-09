package com.example.safebox.features.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.home.presentation.viewmodel.PsychologistHomeViewModel

@Composable
fun PsychologistHomeScreen(navController: NavController, userId: String) {

    val viewModel: PsychologistHomeViewModel = viewModel()

    Column {
        Text(text = "This is psychologist home screen")

        Button(
            onClick = {
                viewModel.onSignOut {
                    navController.navigate(route = "SignInScreen"){
                        popUpTo(route = "SignInScreen"){
                            inclusive = true
                        }
                    }
                }
            }
        ) {
            Text(text = "SignOut")
        }
    }
}