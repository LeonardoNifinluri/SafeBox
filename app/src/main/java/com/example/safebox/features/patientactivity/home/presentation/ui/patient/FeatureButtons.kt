package com.example.safebox.features.patientactivity.home.presentation.ui.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.safebox.R
import com.example.safebox.features.fikri.FeatureButton
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO

/*
@Note
1. navigate to konsul psikolog
2. navigate to diary
3. when user click status mental, should pop something
*/

@Composable
fun FeatureButtons(
    navController: NavController,
    hideBottomNavBar: () -> Unit
) {
    val backgroundColor = Color(0xFFFABC3F)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureButton(
                        iconRes = R.drawable.logo_konsul_psikolog,
                        text = "Konsul\nPsikolog",
                        onClick = {
                            //this is use navController to navigate to konsulPsikolog page
                            navController.navigate(route = PatientScreensDO.Consultation.screen)
                        }
                    )
                    FeatureButton(
                        iconRes = R.drawable.logo_diary_keseharianku,
                        text = "Diary\nKeseharianku",
                        onClick = {
                            //this is use navController to navigate to diary page
                            hideBottomNavBar()
                            navController.navigate(route = PatientScreensDO.CreateDiary.screen)
                        }
                    )
                    FeatureButton(
                        iconRes = R.drawable.logo_status_mentalku,
                        text = "Status\nMentalku",
                        onClick = {
                            //this is will pop something about mentality
                        }
                    )
                }
            }
        }

    }
}