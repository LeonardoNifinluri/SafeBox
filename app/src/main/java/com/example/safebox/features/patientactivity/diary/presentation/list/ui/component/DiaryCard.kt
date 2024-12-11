package com.example.safebox.features.patientactivity.diary.presentation.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R
import com.example.safebox.features.patientactivity.diary.domain.model.Diary

@Composable
fun DiaryCard(
    diary: Diary,
    onClick: () -> Unit
) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = backgroundColor
    ) {
        Card (
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    start = 18.dp,
                    end = 18.dp,
                    bottom = 12.dp
                ).clickable { onClick() },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Column (
                modifier = Modifier.padding(20.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.logo_notes),
                        contentDescription = "Logo Note",
                        modifier = Modifier.size(32.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = diary.createdAt,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = diary.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}