package com.example.safebox.features.patientactivity.consultation.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.BookButton
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.ExperienceSection
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.HeroSection
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.InformationSection
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.ModalSection
import com.example.safebox.features.patientactivity.consultation.presentation.viewmodel.PsychologistDetailViewModel

@Composable
fun PsychologistDetailScreen(
    viewModel: PsychologistDetailViewModel = viewModel(),
    userId: String,
    navController: NavController
) {
    val psychologistState by viewModel.psychologistState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchPsychologistById(userId = userId)
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        when(psychologistState){
            is Result.Loading -> {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Loading psychologist data")
                }
            }
            is Result.Success -> {
                val psychologist = (psychologistState as Result.Success<Psychologist>).data
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item{
                            HeroSection(
                                name = psychologist.name,
                                specializations = psychologist.specializations,
                                imageUrl = psychologist.profileImage,
                                navController = navController
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
                            BookButton(
                                onClick = {
                                    viewModel.onChooseDay()
                                }
                            )
                        }
                    }
                    if(viewModel.showModal.value){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .align(Alignment.Center)
                        ){
                            ModalSection(
                                availability = psychologist.availability,
                                onDismiss = {
                                    viewModel.onCancelChooseDay()
                                    Log.d("DismissModal", "Yes")
                                },
                                onSelected = { selectedDay ->
                                    Log.d("SelectedDay", "Day selected is: $selectedDay")
                                }
                            )
                        }
                    }
                }
            }
            is Result.Empty -> {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "No psychologist data with id: #$userId")
                }
            }
            is Result.Error -> {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Error: ${(psychologistState as Result.Error).exception.message}")
                }
            }

        }
    }
}








