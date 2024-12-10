package com.example.safebox.features.pyschologistactivity

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safebox.features.pyschologistactivity.dataobject.PsychologistScreensDO
import com.example.safebox.features.pyschologistactivity.home.presentation.ui.PsychologistHomeScreen

@Composable
fun PsychologistActivity(
    authNavController: NavController,
    userId: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PsychologistScreensDO.Home.screen
    ){
        composable(route = PsychologistScreensDO.Home.screen){
            PsychologistHomeScreen(
                authNavController = authNavController,
                navController = navController,
                userId = userId
            )
        }
    }
}