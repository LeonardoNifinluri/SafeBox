package com.example.safebox.features.auth.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
            //this is to show the loading bar but for now initializing
            Text(text = "Initializing")
        }
        !viewModel.isUserSignedIn.value -> {
            //this is login form
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFFABC3F)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize().padding(35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(80.dp))
                        Image(
                            painter = painterResource(R.drawable.logo_safebox),
                            contentDescription = null,
                            modifier = Modifier
                                .width(182.dp)
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "Login",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = signInData.email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            label = {
                                Text(text = "Email")
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_email),
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        val passwordVisible = remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = signInData.password,
                            onValueChange = { viewModel.onPasswordChange(it) },
                            label = {
                                Text(text = "Password")
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_password_safebox),
                                    contentDescription = null
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.Gray
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            visualTransformation = if(passwordVisible.value)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(mask = '*'),
                            trailingIcon = {
                                val image = if(passwordVisible.value)
                                    painterResource(R.drawable.streamline_invisible_1_solid)
                                else
                                    painterResource(R.drawable.streamline_visible_solid)

                                IconButton(
                                    onClick = { passwordVisible.value = !passwordVisible.value }
                                ) {
                                    Icon(
                                        painter = image,
                                        contentDescription = null
                                    )
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                        )
                    }


                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                viewModel.onSubmit{
                                    val userId = viewModel.result.value?.user?.uid ?: ""
                                    val route = "HomeScreen/${viewModel.role.value?.name}/$userId"
                                    navController.navigate(route = route) {
                                        popUpTo(route = "SignInScreen")
                                    }
                                }
                            },
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color(0xFF821131),
                            ),
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            enabled = signInData.email.isNotBlank() && signInData.password.isNotBlank() && !viewModel.isLoading.value
                        ){
                            Text(text = if(viewModel.isLoading.value) "Sedang Masuk ..." else "Masuk")
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "Belum Punya Akun?")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Daftar",
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
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
        }
        else -> {
            //this is when user already signed in
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