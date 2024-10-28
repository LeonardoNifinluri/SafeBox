package com.example.safebox.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.safebox.features.auth.presentation.ui.SignInScreen
import com.example.safebox.features.auth.presentation.ui.SignUpScreen
import com.example.safebox.features.fillprofile.presentation.ui.FillProfileScreen
import com.example.safebox.features.fillprofile.presentation.ui.PatientHomeScreen
import com.example.safebox.features.fillprofile.presentation.ui.PsychologistHomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "SignInScreen"
    ){

        composable(route = "SignInScreen"){
            SignInScreen(navController)
        }

        composable(route = "SignUpScreen"){
            SignUpScreen(navController)
        }

        composable(
            route = "FillProfileScreen/{userId}/{userRole}/{userEmail}",
            arguments = listOf(
                navArgument("userId"){ type = NavType.StringType },
                navArgument("userRole"){ type = NavType.StringType },
                navArgument("userEmail"){ type = NavType.StringType }
            )
        ){
            //EditProfileScreen must receive signUpViewModel
            backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val userRole = backStackEntry.arguments?.getString("userRole")
            val userEmail = backStackEntry.arguments?.getString("userEmail")

            FillProfileScreen(navController, userId!!, userRole!!, userEmail!!)
        }

        composable(
            route = "PatientHomeScreen/{userId}",
            arguments = listOf(
                navArgument(name = "userId"){ type = NavType.StringType }
            )
        ){ backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            PatientHomeScreen(userId = userId!!)
        }

        composable(
            route = "PsychologistHomeScreen/{userId}",
            arguments = listOf(
                navArgument(name = "userId"){ type = NavType.StringType }
            )
        ){ backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            PsychologistHomeScreen(userId = userId!!)
        }
    }
}