package com.example.safebox.features.fillprofile.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PsychologistHomeScreen(userId: String) {
    Column {
        Text(
            text = "Hello, $userId",
            fontSize = 50.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "This is psychologist home screen")
    }
}