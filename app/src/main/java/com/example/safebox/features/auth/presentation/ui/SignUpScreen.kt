package com.example.safebox.features.auth.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.auth.presentation.viewmodel.SignUpViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.safebox.R

@Composable
fun SignUpScreen(navHostController: NavHostController) {

    val viewModel: SignUpViewModel = viewModel()
    val signUpData = viewModel.signUpData

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "SignUp",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
            )

            OutlinedTextField(
                value = signUpData.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = {
                    Text(text = "Email")
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )

            val passwordVisible = remember { mutableStateOf(false) }
            OutlinedTextField(
                value = signUpData.password,
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

            //for role
            var isChecked1 by remember { mutableStateOf(false) }
            var isChecked2 by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isChecked1,
                    onCheckedChange = {
                        isChecked1 = it

                        //uncheck the other
                        isChecked2 = !it

                        //update the role
                        viewModel.onRoleChange(Role.PATIENT)
                    } // Updates the state when clicked
                )
                Text(
                    text = "Patient",
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.width(30.dp))

                Checkbox(
                    checked = isChecked2,
                    onCheckedChange = {
                        isChecked2 = it

                        //uncheck the other
                        isChecked1 = !it

                        //update the role
                        viewModel.onRoleChange(Role.PSYCHOLOGIST)
                    } // Updates the state when clicked
                )

                Text(
                    text = "Psychologist",
                    textAlign = TextAlign.Start
                )
            }

            Button(
                onClick = {
                    //Log.d("Button Pressed", "${signUpData.email}, ${signUpData.password}, ${signUpData.role.name}, ")
                    //this is will be the register method in the view model
                    viewModel.onSubmit{
                        try{
                            val userId = viewModel.result?.user?.uid ?: ""

                            //make sure when user press back after success signup, the app closes
                            navHostController.navigate(route = "FillProfileScreen/$userId/${signUpData.role.name}/${signUpData.email}"){
                                popUpTo(route = "SignUpScreen"){
                                    inclusive = true
                                }
                            }

                        }catch (e: Exception){
                            Log.d("SignUpError", "Error")
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                enabled = signUpData.email.isNotBlank() && signUpData.password.isNotBlank() && signUpData.role != Role.UNKNOWN && !viewModel.isLoading
            ){
                Text(text = if(viewModel.isLoading) "Signing Up..." else "Sign Up")
            }

        }
    }
}