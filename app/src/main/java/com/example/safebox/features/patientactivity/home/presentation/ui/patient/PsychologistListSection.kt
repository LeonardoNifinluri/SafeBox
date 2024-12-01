package com.example.safebox.features.patientactivity.home.presentation.ui.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO


@Composable
fun PsychologistListSection(
    psychologistState: Result<List<Psychologist>>,
    navController: NavController,
    hideBottomNavBar: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(color = 0xFFEBEBEB)
    ) {
        when(psychologistState){
            is  Result.Loading -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Memuat data psikolog")
                }
            }

            is Result.Success -> {
                val psychologists = psychologistState.data
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SectionTitle(
                        title = "Konsul ke Psikolog Yuk!",
                    )

                    PsychologistList(
                        psychologists = psychologists,
                        navController = navController
                    ){
                        hideBottomNavBar()
                    }
                    SeeMoreButton(navController = navController){
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
                    Text(text = "Error: ${psychologistState.exception.message}")
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 18.dp, top = 20.dp, bottom = 10.dp),
        color = Color.Black
    )
}

@Composable
fun PsychologistList(
    psychologists: List<Psychologist>,
    navController: NavController,
    hideBottomNavBar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        psychologists.chunked(2).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                chunk.forEach { psychologist ->
                    Box(
                        modifier = Modifier
                            .weight(1f, fill = true),
                        contentAlignment = Alignment.Center
                    ) {
                        //this is the Psychology Card
                        PsychologistCard(
                            name = psychologist.name,
                            specializations = psychologist.specializations,
                            imageUrl = psychologist.profileImage,
                            navController = navController
                        ){
                            hideBottomNavBar()
                        }
                    }
                }
                if (chunk.size == 1) {
                    Spacer(modifier = Modifier.weight(1f)) // Mengisi ruang kosong jika hanya 1 item
                }
            }
        }
    }
}

@Composable
fun SeeMoreButton(
    navController: NavController,
    hideBottomNavBar: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                //this will navigate to other page ()
                hideBottomNavBar()
                navController.navigate(route = PatientScreensDO.Consultation.screen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFABC3F)),
            shape = RoundedCornerShape(8.dp)

        ) {
            Text(
                text = "Lihat Selengkapnya...",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}