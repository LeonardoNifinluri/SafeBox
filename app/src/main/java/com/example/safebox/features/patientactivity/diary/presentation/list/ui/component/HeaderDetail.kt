package com.example.safebox.features.patientactivity.diary.presentation.list.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

@Composable
fun HeaderDetail(
    title: String,
    createdAt: String,
    onBack: () -> Unit
) {
    Surface(
        color = Color(0xFF821131),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
        ){
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_back_3),
                    contentDescription = "Kembali",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    Icon(
                        painter = painterResource(id = R.drawable.logo_tanggal),
                        contentDescription = "logo tanggal",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = createdAt,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }

        }
    }
}