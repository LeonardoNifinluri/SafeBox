package com.example.safebox.features.patientactivity.consultation.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.features.fillprofile.domain.model.psychologist.Experience

@Composable
fun ExperienceSection(experiences: List<Experience>) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 18.dp
            )
        ) {
            Surface (
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp, bottom = 10.dp)
                ){
                    Text(
                        text = "Pengalaman",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(start = 18.dp )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column (
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ){
                        experiences.forEach {
                            ExperienceCard(it)
                        }
                    }
                }
            }
        }
    }
}