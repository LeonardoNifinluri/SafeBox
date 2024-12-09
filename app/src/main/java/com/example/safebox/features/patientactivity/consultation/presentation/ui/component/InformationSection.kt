package com.example.safebox.features.patientactivity.consultation.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

@Composable
fun InformationSection(
    workLocation: String,
    email: String,
    phoneNumber: String
) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface (
        modifier = Modifier
            .fillMaxWidth(),
        color = backgroundColor
    ){
        Column (
            modifier = Modifier.padding(18.dp)
        ){
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Informasi Kontak",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.logo_lokasi),
                            contentDescription = "logo lokasi",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = workLocation,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Icon(
                            painter = painterResource(R.drawable.logo_email),
                            contentDescription = "logo email",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = email,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Icon(
                            painter = painterResource(R.drawable.logo_telephone),
                            contentDescription = "logo telephone",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = phoneNumber,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}
