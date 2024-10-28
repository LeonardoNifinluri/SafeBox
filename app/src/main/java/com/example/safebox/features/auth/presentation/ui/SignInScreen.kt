package com.example.safebox.features.auth.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.R
import com.example.safebox.features.auth.presentation.viewmodel.SignInViewModel


@Composable
fun SignInScreen(navController: NavController) {

    val viewModel: SignInViewModel = viewModel()
    val signInData = viewModel.signInData

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "SignIn",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
            )

            OutlinedTextField(
                value = signInData.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = {
                    Text(text = "Email")
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )

            val passwordVisible = remember { mutableStateOf(false) }
            OutlinedTextField(
                value = signInData.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = {
                    Text(text = "Password")
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                visualTransformation = if(passwordVisible.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(mask = '*'),
                trailingIcon = {
                    val image = if(passwordVisible.value)
                        painterResource(R.drawable.ic_password_invisible)
                    else
                        painterResource(R.drawable.ic_password_visible)

                    IconButton(
                        onClick = { passwordVisible.value = !passwordVisible.value }
                    ) {
                        Icon(
                            painter = image,
                            contentDescription = null
                        )
                    }
                }
            )

            Button(
                onClick = {
                    //Log.d("Button Pressed", "${signUpData.email}, ${signUpData.password}, ${signUpData.role.name}, ")
                    //this is will be the register method in the view model
                    viewModel.onSubmit{
                        //fetch data using user id
                        val userId = viewModel.result?.user?.uid ?: ""
                        //if role == PATIENT to patient activity else to psychologist activity
                        Log.d("SignIn Status", "Success")
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                enabled = signInData.email.isNotBlank() && signInData.password.isNotBlank() && !viewModel.isLoading
            ){
                Text(text = if(viewModel.isLoading) "Signing In..." else "Sign Ip")
            }

            Button(
                onClick = {
                    navController.navigate(route = "SignUpScreen")
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
            ){
                Text(text = "Sign Up")
            }

        }
    }
}