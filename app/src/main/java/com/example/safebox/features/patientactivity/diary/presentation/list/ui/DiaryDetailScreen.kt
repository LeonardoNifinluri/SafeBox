package com.example.safebox.features.patientactivity.diary.presentation.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel.DiaryDetailScreenViewModel

@Composable
fun DiaryDetailScreen(
    userId: String,
    diaryId: String,
    viewModel: DiaryDetailScreenViewModel = viewModel()
) {
    val diaryState by viewModel.diaryState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDiary(
            userId = userId,
            diaryId = diaryId
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            when(diaryState){
                is Result.Loading -> {
                    Text(text = "Load the diary data")
                }
                is Result.Success -> {
                    val diary = (diaryState as Result.Success<Diary>).data
                    Text(text = diary.id)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = diary.title)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = diary.content)
                    Spacer(modifier = Modifier.height(16.dp))
                    if(diaryId == diary.id){
                        Text(text = "Same")
                    }
                }
                is Result.Empty -> {
                    Text(text = "No diary found with id: $diaryId")
                }
                is Result.Error -> {
                    Text(text = "Error when get diary with id $diaryId: ${(diaryState as Result.Error).exception.message}")
                }
            }
        }
    }
}