package com.example.safebox.features.pyschologistactivity.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.ExperienceSection
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.InformationSection
import com.example.safebox.features.pyschologistactivity.home.presentation.ui.component.AvailableSection
import com.example.safebox.features.pyschologistactivity.home.presentation.ui.component.HeroSection
import com.example.safebox.features.pyschologistactivity.home.presentation.ui.component.SignOutButton
import com.example.safebox.features.pyschologistactivity.home.presentation.viewmodel.PsychologistHomeScreenViewModel

@Composable
fun PsychologistHomeScreen(
    authNavController: NavController,
    navController: NavController,
    userId: String,
    viewModel: PsychologistHomeScreenViewModel = viewModel()
) {

    val psychologistState by viewModel.psychologistState.collectAsState()
    val backgroundColor = Color(0xFFEBEBEB)

    //authNavController is used to do signOut
    //navController is used to navigation between screens in psychologist activity
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchPsychologistById(userId = userId)
    }
    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = backgroundColor
        ) {
            when(psychologistState){
                is Result.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "Loading your data")
                    }
                }
                is Result.Success -> {
                    val psychologist = (psychologistState as Result.Success<Psychologist>).data
                    Box(modifier = Modifier.fillMaxSize()
                    ){
                        LazyColumn (
                            modifier = Modifier.fillMaxSize()
                        ){
                            item{
                                HeroSection(
                                    name = psychologist.name,
                                    specializations = psychologist.specializations,
                                    imageUrl = psychologist.profileImage
                                )
                            }
                            item{
                                InformationSection(
                                    workLocation = psychologist.workLocation,
                                    email = psychologist.email,
                                    phoneNumber = psychologist.phoneNumber
                                )
                            }
                            item{
                                ExperienceSection(experiences = psychologist.experiences)
                            }
                            item{
                                AvailableSection(availability = psychologist.availability)
                            }
                            item{
                                SignOutButton(
                                    onClick = {
                                        viewModel.signOut {
                                            authNavController.navigate(route = "SignInScreen"){
                                                popUpTo(0){
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                is Result.Empty -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "No psychologist found with id $userId")
                    }
                }
                is Result.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "Error: ${(psychologistState as Result.Error).exception.message}")
                    }
                }
            }
        }
    }
}