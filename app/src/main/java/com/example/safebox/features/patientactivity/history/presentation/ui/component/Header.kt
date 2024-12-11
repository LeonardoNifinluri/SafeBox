package com.example.safebox.features.patientactivity.history.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                color = Color(0xFFFABC3F),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, // Mengubah alignment ke Start untuk memastikan elemen sejajar kiri
                    verticalArrangement = Arrangement.Center // Agar semua elemen berada di atas
                ) {
                    Text(
                        text = "Riwayat Konsultasi",
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
                        text = "Kamu aman bercerita dengan orang-orang ini",
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