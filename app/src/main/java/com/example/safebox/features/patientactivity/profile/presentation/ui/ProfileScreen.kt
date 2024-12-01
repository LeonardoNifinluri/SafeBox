package com.example.safebox.features.patientactivity.profile.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.patientactivity.home.data.repository.FirebaseRepositoryImpl
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase
import com.example.safebox.features.patientactivity.profile.presentation.viewmodel.ProfileViewModel
import com.example.safebox.features.patientactivity.profile.presentation.viewmodel.ProfileViewModelFactory

/*
* name
* image
* patient
* phone number
* email
* birthdate
* gender
* address
*/
@Composable
fun ProfileScreen(
    authNavController: NavController,
    userId: String
) {
    val repositoryImpl = FirebaseRepositoryImpl()
    val getPatientDataUseCase = GetPatientDataUseCase(repositoryImpl)
    val factory = ProfileViewModelFactory(
        userId = userId,
        getPatientDataUseCase = getPatientDataUseCase
    )
    val context = LocalContext.current
    val viewModel: ProfileViewModel = ViewModelProvider(context as ComponentActivity, factory)[ProfileViewModel::class.java]
    val patientState by viewModel.patientState.collectAsState()
    val backgroundColor = Color(0xFFEBEBEB)
    LaunchedEffect(key1 = Unit) {
        viewModel.onRefresh()
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        when(patientState){
            is Result.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Memuat data kamu")
                }
            }
            is Result.Success -> {
                val patient = (patientState as Result.Success<Patient?>).data
                patient?.let{
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        //this is header
                        Surface(
                            color = Color(0xFFFABC3F),
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
                        ){
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Profil Kamu",
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.Black
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ) {
                            val painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = patient.profileImageURL).apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                    }).build()
                            )
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier.size(100.dp).clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Text(
                                    text = patient.name,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = patient.role.name,
                                    color = Color.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //this is phone number
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = patient.phoneNumber,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //this is email
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = patient.email,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //this is birthdate
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = patient.birthdate,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //this is gender
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = patient.gender.name,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        //this is address
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = patient.address,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
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
                }
            }
            is Result.Empty -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Data tidak ditemukan")
                }
            }
            is Result.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error: ${(patientState as Result.Error).exception.message}")
                }
            }
        }
    }
}

