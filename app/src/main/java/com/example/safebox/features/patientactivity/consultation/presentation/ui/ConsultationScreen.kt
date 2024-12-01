package com.example.safebox.features.patientactivity.consultation.presentation.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.safebox.R
import com.example.safebox.core.result.Result
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.presentation.viewmodel.ConsultationViewModel
import com.example.safebox.features.patientactivity.consultation.presentation.viewmodel.ConsultationViewModelFactory
import com.example.safebox.features.patientactivity.home.data.repository.FirebaseRepositoryImpl
import com.example.safebox.features.patientactivity.home.domain.usecase.GetPsychologistDataUseCase
import com.example.safebox.features.patientactivity.home.presentation.ui.patient.PsychologistList

@Composable
fun ConsultationScreen(
    navController: NavController,
    hideBottomNavBar: () -> Unit
) {
    val repositoryImpl = FirebaseRepositoryImpl()
    val getPsychologistDataUseCase = GetPsychologistDataUseCase(repositoryImpl)
    val factory = ConsultationViewModelFactory(getPsychologistDataUseCase)
    val context = LocalContext.current
    val viewModel: ConsultationViewModel = ViewModelProvider(context as ComponentActivity, factory)[ConsultationViewModel::class.java]
    val psychologistState by viewModel.psychologistState.collectAsState()
    val backgroundColor = Color(0xFFEBEBEB)

    LaunchedEffect(key1 = Unit) {
        viewModel.onRefresh()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            item {
                Header(navController = navController)
            }
            item{
                when(psychologistState){
                    is Result.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Memuat data psikolog")
                        }
                    }

                    is Result.Success -> {
                        val psychologists = (psychologistState as Result.Success<List<Psychologist>>).data
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp)
                        ) {
                            PsychologistList(
                                psychologists = psychologists,
                                navController = navController
                            ) {
                                hideBottomNavBar()
                            }
                        }
                    }

                    is Result.Empty -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Belum ada data psikolog")
                        }
                    }

                    is Result.Error -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Error: ${(psychologistState as Result.Error).exception.message}")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Header(navController: NavController) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                color = Color(0xFFFABC3F), // Menetapkan warna latar belakang
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)// Pastikan Surface mengisi lebar layar
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, // Mengubah alignment ke Start untuk memastikan elemen sejajar kiri
                    verticalArrangement = Arrangement.Center // Agar semua elemen berada di atas
                ) {
                    // Tombol Kembali
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.Start) // Sejajarkan tombol ke kiri
                            .padding(start = 2.dp)// Menghilangkan padding tambahan
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.logo_back_3),
                            contentDescription = "logo kembali",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Konsul Psikolog",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()// Teks diselaraskan ke kiri
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    // Teks tambahan
                    Text(
                        text = "Yuk ceritakan keluh kesah mu dengan psikolog !",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 5.dp)
                    )
                }
            }

        }
    }

}