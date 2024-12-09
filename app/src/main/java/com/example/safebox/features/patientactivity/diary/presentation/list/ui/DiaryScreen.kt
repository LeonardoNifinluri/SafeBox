package com.example.safebox.features.patientactivity.diary.presentation.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.DiaryCard
import com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel.DiaryScreenViewModel

@Composable
fun DiaryScreen(
    userId: String,
    viewModel: DiaryScreenViewModel = viewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllDiary(userId = userId)
    }
    val diaryState by viewModel.diaryState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize().background(color = Color(0xFFEBEBEB))
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "This is Note Screen",
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            when(diaryState){
                is Result.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Loading diary data")
                    }
                }
                is Result.Success -> {
                    val diaries = (diaryState as Result.Success<List<Diary>>).data
                    LazyColumn {
                        items(diaries){
                            diary ->
                            DiaryCard(diary = diary)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                is Result.Error -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Error: ${(diaryState as Result.Error).exception.message}")
                    }
                }
                is Result.Empty -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No diary data")
                    }
                }
            }
        }
    }
}
