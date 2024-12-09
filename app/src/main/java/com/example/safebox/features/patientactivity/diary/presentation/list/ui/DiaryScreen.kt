package com.example.safebox.features.patientactivity.diary.presentation.list.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.AddDiaryCard
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.DiaryCard
import com.example.safebox.features.patientactivity.diary.presentation.list.ui.component.Header
import com.example.safebox.features.patientactivity.diary.presentation.list.viewmodel.DiaryScreenViewModel

@Composable
fun DiaryScreen(
    userId: String,
    viewModel: DiaryScreenViewModel = viewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllDiary(userId = userId)
    }
    val diaryState by viewModel.diaryState.collectAsState()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFEBEBEB))
    ){
        when(diaryState){
            is Result.Loading -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Loading diary data")
                }
            }
            is Result.Success -> {
                val diaries = (diaryState as Result.Success<List<Diary>>).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    //HeaderDiaryKesehariasnku : Header
                    item{
                        Header(
                            onBackClick = {
                                Log.d("BackStatus", "Clicked")
                            }
                        )
                    }
                    item{
                        AddDiaryCard(
                            onClick = {
                                Log.d("AddDiaryStatus", "Clicked")
                                navController.navigate(route = PatientScreensDO.CreateDiary.screen)
                            }
                        )
                    }
                    items(diaries){ diary ->
                        DiaryCard(
                            diary = diary,
                            onClick = {
                                Log.d("DiaryCardStatus", "Clicked with id ${diary.id}")
                            }
                        )
                    }
                    item{
                        Box(
                            modifier = Modifier
                                .height(12.dp)
                                .background(Color(0xFFEBEBEB))
                                .fillMaxWidth()
                        )
                    }

                }

            }
            is Result.Error -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Error: ${(diaryState as Result.Error).exception.message}")
                }
            }
            is Result.Empty -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "No diary data")
                }
            }
        }
    }
}


