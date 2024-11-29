package com.example.safebox.features.patientactivity.profile.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.patientactivity.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(authNavController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This is Profile Screen")
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.signOut {
                        authNavController.navigate(route = "SignInScreen"){
                            popUpTo(0){
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
}