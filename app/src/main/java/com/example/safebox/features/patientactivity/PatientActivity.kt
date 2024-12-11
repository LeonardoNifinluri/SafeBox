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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.safebox.R
import com.example.safebox.features.patientactivity.consultation.presentation.ui.ConsultationScreen
import com.example.safebox.features.patientactivity.consultation.presentation.ui.PsychologistDetailScreen
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO
import com.example.safebox.features.patientactivity.diary.presentation.form.ui.CreateDiaryScreen
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.DiaryDetailScreen
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.DiaryScreen
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
    val showBottomBar = remember{ mutableStateOf(value = true) }

    // Observe navigation changes to dynamically show/hide the bottom bar
    DisposableEffect(navController) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            // Update visibility based on the current screen
            showBottomBar.value = when (destination.route) {
                PatientScreensDO.Home.screen,
                PatientScreensDO.Note.screen,
                PatientScreensDO.History.screen,
                PatientScreensDO.Profile.screen -> true
                else -> false
            }
        }
        navController.addOnDestinationChangedListener(callback)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }
    Scaffold(
        bottomBar = {
            if(showBottomBar.value){
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
                                tint = if(selected.intValue == R.drawable.logo_beranda) Color(0xFFFABC3F) else Color.White
                            )
                        }
                        Text(
                            text = "Beranda",
                            fontSize = 10.sp,
                            color = if(selected.intValue == R.drawable.logo_beranda) Color(0xFFFABC3F) else Color.White,
                            fontWeight = if(selected.intValue == R.drawable.logo_beranda) FontWeight.Bold else FontWeight.Normal
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
                                tint = if(selected.intValue == R.drawable.logo_diarykeseharianku) Color(0xFFFABC3F) else Color.White,
                            )
                        }
                        Text(
                            text = "Catatan",
                            fontSize = 10.sp,
                            color = if(selected.intValue == R.drawable.logo_diarykeseharianku) Color(0xFFFABC3F) else Color.White,
                            fontWeight = if(selected.intValue == R.drawable.logo_diarykeseharianku) FontWeight.Bold else FontWeight.Normal
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
                                tint = if(selected.intValue == R.drawable.logo_konsulpsikolog) Color(0xFFFABC3F) else Color.White
                            )
                        }
                        Text(
                            text = "Riwayat",
                            fontSize = 10.sp,
                            color = if(selected.intValue == R.drawable.logo_konsulpsikolog) Color(0xFFFABC3F) else Color.White,
                            fontWeight = if(selected.intValue == R.drawable.logo_konsulpsikolog) FontWeight.Bold else FontWeight.Normal
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
                                tint = if(selected.intValue == R.drawable.logo_profil) Color(0xFFFABC3F) else Color.White
                            )
                        }
                        Text(
                            text = "Profil",
                            fontSize = 10.sp,
                            color = if(selected.intValue == R.drawable.logo_profil) Color(0xFFFABC3F) else Color.White,
                            fontWeight = if(selected.intValue == R.drawable.logo_profil) FontWeight.Bold else FontWeight.Normal
                        )
                    }

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
                    userId = userId,
                    hideBottomNavBar = {
                        showBottomBar.value = false
                    }
                )
            }
            composable(route = PatientScreensDO.Note.screen){
                DiaryScreen(
                    userId = userId,
                    navController = navController
                )
            }
            composable(route = PatientScreensDO.History.screen){
                HistoryScreen(
                    userId = userId,
                )
            }
            composable(
                route = PatientScreensDO.Profile.screen
            ){
                ProfileScreen(
                    authNavController = authNavController,
                    userId = userId
                )
            }
            composable(route = PatientScreensDO.Consultation.screen){
                ConsultationScreen(navController = navController)
            }
            composable(route = PatientScreensDO.CreateDiary.screen){
                CreateDiaryScreen(
                    navController = navController,
                    userId = userId
                )
            }

            //this is for psychologist detail using psychologist user id
            composable(
                route = "${PatientScreensDO.Consultation.screen}/detail/{userId}",
                arguments = listOf(
                    navArgument(name = "userId"){ type = NavType.StringType}
                )
            ){navBackStackEntry ->
                val psychologistUserId = navBackStackEntry.arguments?.getString("userId")!!
                PsychologistDetailScreen(
                    userId = userId,
                    psychologistId = psychologistUserId,
                    navController = navController
                )
            }

            //this is for detail diary
            composable(
                route = "${PatientScreensDO.DetailDiary.screen}/{diaryId}",
                arguments = listOf(
                    navArgument(name = "diaryId"){ type = NavType.StringType }
                )
            ){navBackStackEntry ->
                val diaryId = navBackStackEntry.arguments?.getString("diaryId")!!
                DiaryDetailScreen(
                    userId = userId,
                    diaryId = diaryId,
                    navController = navController
                )
            }
        }
    }
}