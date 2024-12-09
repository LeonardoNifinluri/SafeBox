package com.example.safebox.features.patientactivity.consultation.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.presentation.viewmodel.PsychologistDetailViewModel

@Composable
fun PsychologistDetailScreen(
    viewModel: PsychologistDetailViewModel = viewModel(),
    userId: String
) {
    val psychologistState by viewModel.psychologistState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchPsychologistById(userId = userId)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when(psychologistState){
                is Result.Loading -> {
                    Text(text = "Loading psychologist data")
                }
                is Result.Success -> {
                    val psychologist = (psychologistState as Result.Success<Psychologist>).data
                    Text(text = psychologist.name)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.id)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.phoneNumber)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.email)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.workLocation)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.availability.toString())
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = psychologist.experiences.toString())
                    Spacer(modifier = Modifier.height(20.dp))
                }
                is Result.Empty -> {
                    Text(text = "No psychologist data with id: #$userId")
                }
                is Result.Error -> {
                    Text(text = "Error: ${(psychologistState as Result.Error).exception.message}")
                }

            }
        }
    }

}