package com.example.safebox.features.patientactivity.home.presentation.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

/*
@Note
when user sliding, it should pop something
*/
@Composable
fun MoodSlider() {
    var moodValue by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF821131)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bagaimana Kondisi Mental\nKamu Hari Ini? ",
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 23.sp,
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Slider(
                value = moodValue,
                onValueChange = {moodValue = it},
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Yellow,
                    inactiveTickColor = Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Icon(
                painter = painterResource(R.drawable.logo_smile_slider_pilihan2),
                contentDescription = "Emoji Senyum",
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (moodValue * 3.25).dp),
                tint = Color.White
            )

        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}