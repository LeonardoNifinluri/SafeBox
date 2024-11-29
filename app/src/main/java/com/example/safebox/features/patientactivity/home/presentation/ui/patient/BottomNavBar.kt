package com.example.safebox.features.patientactivity.home.presentation.ui.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.safebox.R
import com.example.safebox.features.patientactivity.home.domain.model.BottomNavItem

@Composable
fun BottomNavBar(
    navController: NavController,
    selectedRoute: List<String>
) {

    val items = listOf(
        BottomNavItem("Beranda", R.drawable.logo_beranda, "beranda"),
        BottomNavItem("Konsul\nPsikolog", R.drawable.logo_konsulpsikolog, "route"),
        BottomNavItem("Diary\nKeseharianku", R.drawable.logo_diarykeseharianku, "diary"),
        BottomNavItem("Status\nMentalku", R.drawable.logo_statusmentalku, "status"),
        BottomNavItem(" Profil ", R.drawable.logo_profil, "profil")
    )


    Surface(
        color = Color(0xFFC7253E),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(vertical = 13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy((-0.6).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, bottomNavItem ->
                    NavItem(
                        iconRes = bottomNavItem.icon,
                        text = bottomNavItem.title,
                        isSelected = bottomNavItem.route == selectedRoute[index]
                    ) {
                        //this will navigate user to desired page by icon
                    }
                }
            }
        }
    }

}