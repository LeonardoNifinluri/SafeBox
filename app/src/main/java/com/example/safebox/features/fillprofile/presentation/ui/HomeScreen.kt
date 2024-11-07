package com.example.safebox.features.fillprofile.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.safebox.features.auth.domain.model.Role

@Composable
fun HomeScreen(userId: String, userRole: String) {

    Box{
        Column {
            Text(text = userId)

            Spacer(modifier = Modifier.height(30.dp))

            when(Role.valueOf(userRole)){
                Role.PATIENT -> Text(text = "This is patient")
                Role.PSYCHOLOGIST -> Text(text = "This is psychologist")
                else -> Text(text = "This is unknown")
            }

            Spacer(modifier = Modifier.height(30.dp))
            
            Text(text = "Hello this is home page")
        }
    }
}