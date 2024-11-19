package com.example.safebox.features.auth.presentation.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.R
import com.example.safebox.features.auth.presentation.viewmodel.SignInViewModel

@Composable
fun SignInScreen(navController: NavController) {

    val viewModel: SignInViewModel = viewModel()
    val signInData = viewModel.signInData.value

    //use the viewModel.isInitializing

    when{
        viewModel.isInitializing.value -> {
            Text(text = "Initializing")
        }
        !viewModel.isUserSignedIn.value -> {
            //this is when no user signed in
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
                                //if role == PATIENT to patient activity else to psychologist activity
                                //we have to get the role
                                val userId = viewModel.result.value?.user?.uid ?: ""
                                Log.d(
                                    "SignIn Status",
                                    "Success"
                                )
                                val route = "HomeScreen/${viewModel.role.value?.name}/$userId"
                                navController.navigate(route = route){
                                    popUpTo(route = "SignInScreen")
                                }
                                Log.d(
                                    "SignIn",
                                    "$userId : ${viewModel.role.value?.name}, ${viewModel.isUserSignedIn.value}"
                                )
                            }

                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        enabled = signInData.email.isNotBlank() && signInData.password.isNotBlank() && !viewModel.isLoading.value
                    ){
                        Text(text = if(viewModel.isLoading.value) "Signing In..." else "Sign In")
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "Do not have an account?")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "SingUp here",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                navController.navigate(route = "SignUpScreen")
                            }
                        )
                    }

                    (if(viewModel.message.value != null) viewModel.message.value else "")?.let {
                        Text(
                            text = it,
                            color = Color.Red
                        )
                    }
                }
            }
        }
        else -> {
            //this is when a user signed in
//            Text(text = "Already Signed In, with role : ${viewModel.role.value?.name}")
            val userRole = viewModel.role.value?.name
            val userId = viewModel.userId.value
            val route = "HomeScreen/$userRole/$userId"
            navController.navigate(route = route){
                popUpTo(route = "SignInScreen"){
                    inclusive = true
                }
                //this is to make sure only one instance will be made
                launchSingleTop = true
            }
        }
    }
}