package com.example.safebox.features.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.home.presentation.viewmodel.UnknownUserViewModel

@Composable
fun UnknownUserScreen(navController: NavController, userId: String) {

    val viewModel: UnknownUserViewModel = viewModel()

    Box{
        Column {
            Text(text = "Unknown User")
            Button(
                onClick = {
                    viewModel.onSignOut {
                        navController.navigate(route = "SignInScreen"){
                            popUpTo(id = 0){
                                inclusive = true
                            }
                        }
                    }
                }
            ){
                Text(text = "SignOut")
            }
        }
    }
}