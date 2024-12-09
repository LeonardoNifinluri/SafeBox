package com.example.safebox.features.patientactivity.diary.presentation.list.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

@Composable
fun AddDiaryCard(onClick: () -> Unit) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface (
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ){
        Column (
            modifier = Modifier
                .padding(
                    top = 22.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 12.dp
                )
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(130.dp) // Sesuaikan tinggi sesuai kebutuhan
                    .background(Color(0xFF821131)) // Warna latar belakang merah
                    .clickable(onClick = onClick)
                    .padding(5.dp)
            ){
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    val dashWidth = 35f
                    val dashGap = 15f
                    val strokeWidth = 7f

                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)
                    drawRoundRect(
                        color = Color.Gray,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(20f, 20f),
                        style = Stroke(width = strokeWidth, pathEffect = pathEffect)
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Menambahkan padding pada konten
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Text(
                        text = "Tambah\nDiary Keseharianku",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(65.dp))
                    Icon(
                        painter = painterResource(R.drawable.logo_tambahdiary),
                        contentDescription = "Logo Tambah Diary",
                        tint = Color.White,
                        modifier = Modifier.size(38.dp)
                    )
                }
            }
        }
    }
}