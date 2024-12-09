package com.example.safebox.features.patientactivity.consultation.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModalSection(
    availability: List<Boolean>,
    onDismiss: () -> Unit,
    onSelected: (String) -> Unit
) {
    val daysOfWeek = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit){
                detectTapGestures(onPress = {})
            }
    ){
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.Transparent),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF821131))
                    .padding(vertical = 18.dp, horizontal = 20.dp)
                    .heightIn(min = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Jadwal Yang Tersedia",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 10.dp, bottom = 30.dp),
                    textAlign = TextAlign.Center
                )
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    daysOfWeek.forEachIndexed { index, day ->
                        if(availability[index]){
                            Button(
                                onClick = { onSelected(day) },
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(15.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFABC3F))
                            ) {
                                Text(
                                    text = day,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "Kembali",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
