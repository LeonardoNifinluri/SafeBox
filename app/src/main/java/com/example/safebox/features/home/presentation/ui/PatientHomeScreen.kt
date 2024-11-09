package com.example.safebox.features.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.home.presentation.viewmodel.PatientHomeViewModel

@Composable
fun PatientHomeScreen(navController: NavController, userId: String) {

    var data by remember { mutableStateOf(value = "") }

    val viewModel: PatientHomeViewModel = viewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Patient Home Screen",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
            )

            Text(text = userId)

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = {
                    Text(text = "Patient Data")
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )

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
            ){
                Text(text = "SignOut")
            }

        }

    }
}