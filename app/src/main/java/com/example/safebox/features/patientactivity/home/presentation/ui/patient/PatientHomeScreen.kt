package com.example.safebox.features.patientactivity.home.presentation.ui.patient


import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPatientDataUseCase
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase
import com.example.safebox.features.patientactivity.home.presentation.viewmodel.PatientHomeViewModel
import com.example.safebox.features.patientactivity.home.presentation.viewmodel.PatientHomeViewModelFactory

@Composable
fun PatientHomeScreen(
    navController: NavController,
    userId: String
) {
    val repositoryImpl =
        com.example.safebox.features.patientactivity.home.data.repository.FirebaseRepositoryImpl()
    val getPatientDataUseCase = GetPatientDataUseCase(repositoryImpl)
    val getPsychologistDataUseCase = GetPsychologistDataUseCase(repositoryImpl)
    val factory = PatientHomeViewModelFactory(
        userId =  userId,
        getPatientDataUseCase = getPatientDataUseCase,
        getPsychologistDataUseCase = getPsychologistDataUseCase
    )
    val context = LocalContext.current
    val viewModel: PatientHomeViewModel = ViewModelProvider(context as ComponentActivity, factory)[PatientHomeViewModel::class.java]
    val patientState by viewModel.patientState.collectAsState()
    val psychologistState by viewModel.psychologistState.collectAsState()
    
    LaunchedEffect(key1 = Unit) {
        viewModel.onRefresh()
    }

    when(patientState){

        //this is when state is loading
        is Result.Loading -> {
            Text(text = "Initializing")
        }

        //this is when the state is success
        is Result.Success -> {
            val patient = (patientState as Result.Success<Patient?>).data
            patient?.let{
                //this is display the home page

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ){
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item{
                            Header(
                                name = it.name,
                                imageUrl = it.profileImageURL
                            )
                        }
                        item{
                            FeatureButtons(navController = navController)
                        }
                        item{
                            MoodSlider()
                        }
                        item{
                            //this is for psychologist list
                            PsychologistListSection(
                                psychologistState = psychologistState,
                                navController = navController
                            )
                        }
                    }
                }

            } ?: Text(text = "No patient data available.")  //this is show when the patient data is null
        }

        //this is when state is empty
        is Result.Empty -> {
            Text(text = "No patient data found.")
        }

        //this is when state is error
        is Result.Error -> {
            Text(text = "Error: ${(patientState as Result.Error).exception.message}")
        }
    }
}