package com.example.safebox.features.patientactivity.diary.presentation.list.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.safebox.features.patientactivity.diary.domain.model.Diary

@Composable
fun DiaryCard(diary: Diary) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(text = diary.id)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = diary.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = diary.createdAt)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = diary.content)
            Spacer(modifier = Modifier.height(16.dp))

        }
    }

}