package com.example.safebox.features.auth.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.safebox.ui.theme.MainColor

@Composable
fun SignUpScreen(navHostController: NavHostController) {

    val viewModel: SignUpViewModel = viewModel()
    val signUpData = viewModel.signUpData.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainColor)
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
            ){
                Spacer(modifier = Modifier.height(80.dp))
                Image(
                    painter = painterResource(R.drawable.logo_safebox),
                    contentDescription = null,
                    modifier = Modifier
                        .width(182.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Register",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = signUpData.email,
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
                    value = signUpData.password,
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
                    shape = RoundedCornerShape(12.dp),
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
                            isChecked2 = !it
                            viewModel.onRoleChange(Role.PATIENT)
                        },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.Gray,
                            checkedColor = Color.LightGray
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.patient),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Checkbox(
                        checked = isChecked2,
                        onCheckedChange = {
                            isChecked2 = it
                            isChecked1 = !it
                            viewModel.onRoleChange(Role.PSYCHOLOGIST)
                        },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.Gray,
                            checkedColor = Color.LightGray
                        )
                    )

                    Text(
                        text = stringResource(id = R.string.psychologist),
                        textAlign = TextAlign.Start
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {
                        viewModel.onSubmit{
                            try{
                                val userId = viewModel.result.value?.user?.uid ?: ""
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
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF821131),
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = signUpData.email.isNotBlank() && signUpData.password.isNotBlank() && signUpData.role != Role.UNKNOWN && !viewModel.isLoading.value
                ){
                    Text(text = if(viewModel.isLoading.value) stringResource(id = R.string.signing_up) else stringResource(id = R.string.signup))
                }

                //this is for error message
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