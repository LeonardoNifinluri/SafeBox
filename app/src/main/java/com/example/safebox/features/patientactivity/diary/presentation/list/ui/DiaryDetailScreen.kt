package com.example.safebox.features.patientactivity.diary.presentation.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.Content
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.HeaderDetail
import com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel.DiaryDetailScreenViewModel

@Composable
fun DiaryDetailScreen(
    userId: String,
    diaryId: String,
    viewModel: DiaryDetailScreenViewModel = viewModel(),
    navController: NavController
) {
    val diaryState by viewModel.diaryState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDiary(
            userId = userId,
            diaryId = diaryId
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFEBEBEB)
    ) {
        Column (
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
        ){
            when(diaryState){
                is Result.Loading -> {
                    Text(text = "Load the diary data")
                }
                is Result.Success -> {
                    val diary = (diaryState as Result.Success<Diary>).data
                    LazyColumn {
                        item {
                            HeaderDetail(
                                title = diary.title,
                                onBack = {
                                    navController.popBackStack()
                                },
                                createdAt = diary.createdAt
                            )
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .height(16.dp)
                                    .background(Color(0xFFEBEBEB))
                                    .fillMaxWidth()
                            )
                        }
                        item {
                            Content(
                                content = diary.content
                            )
                        }
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