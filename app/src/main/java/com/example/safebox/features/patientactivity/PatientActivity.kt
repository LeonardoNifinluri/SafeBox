package com.example.safebox.features.patientactivity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safebox.R
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO
import com.example.safebox.features.patientactivity.diary.presentation.ui.DiaryScreen
import com.example.safebox.features.patientactivity.history.presentation.ui.HistoryScreen
import com.example.safebox.features.patientactivity.home.presentation.ui.patient.PatientHomeScreen
import com.example.safebox.features.patientactivity.profile.presentation.ui.ProfileScreen

@Composable
fun PatientActivity(
    authNavController: NavController,
    userId: String
) {
    val navController = rememberNavController()
    val selected = remember{
        mutableIntStateOf(value = R.drawable.logo_beranda)
    }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(color = 0xFFC7253E)
            ) {
                //this is for home
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.logo_beranda
                            navController.navigate(PatientScreensDO.Home.screen){
                                popUpTo(0)
                            }
                        },
                        enabled = selected.intValue != R.drawable.logo_beranda
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_beranda),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Beranda",
                        fontSize = 10.sp
                    )
                }

                //this is for note
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.logo_diarykeseharianku
                            navController.navigate(PatientScreensDO.Note.screen){
                                popUpTo(0)
                            }
                        },
                        enabled = selected.intValue != R.drawable.logo_diarykeseharianku
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_diarykeseharianku),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Catatan",
                        fontSize = 10.sp
                    )
                }

                //this is for history
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.logo_konsulpsikolog
                            navController.navigate(PatientScreensDO.History.screen){
                                popUpTo(0)
                            }
                        },
                        enabled = selected.intValue != R.drawable.logo_konsulpsikolog
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_konsulpsikolog),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Riwayat",
                        fontSize = 10.sp
                    )
                }


                //this is for profile
                Column(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            selected.intValue = R.drawable.logo_profil
                            navController.navigate(PatientScreensDO.Profile.screen){
                                popUpTo(0)
                            }
                        },
                        enabled = selected.intValue != R.drawable.logo_profil
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logo_profil),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Profil",
                        fontSize = 10.sp
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = PatientScreensDO.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ){
            composable(route = PatientScreensDO.Home.screen){
                PatientHomeScreen(
                    navController = navController,
                    userId = userId
                )
            }
            composable(route = PatientScreensDO.Note.screen){
                DiaryScreen()
            }
            composable(route = PatientScreensDO.History.screen){
                HistoryScreen()
            }
            composable(route = PatientScreensDO.Profile.screen){
                ProfileScreen(authNavController = authNavController)
            }
        }
    }
}