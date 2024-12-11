package com.example.safebox.features.patientactivity.history.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.presentation.ui.component.Header
import com.example.safebox.features.patientactivity.history.presentation.ui.component.HistoryCard
import com.example.safebox.features.patientactivity.history.presentation.viewmodel.HistoryScreenViewModel

@Composable
fun HistoryScreen(
    userId: String,
    viewModel: HistoryScreenViewModel = viewModel()
) {
    val historiesState by viewModel.historiesState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllHistories(patientId = userId)
    }

    when(historiesState){
        is Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "Loading All Histories")
                }
            }
        }
        is Result.Success -> {
            val histories = (historiesState as Result.Success<List<History>>).data
            LazyColumn (
                modifier = Modifier.fillMaxWidth()
            ){
                item{
                    Header()
                }
                item{
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .background(Color(0xFFEBEBEB))
                            .fillMaxWidth()
                    )
                }
                items(histories){ history ->
                    val psychologistName = history.psychologistName
                    val day = viewModel.getDayString(history.day)
                    HistoryCard(
                        psychologistName = psychologistName,
                        day = day
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .background(Color(0xFFEBEBEB))
                            .fillMaxWidth()
                    )
                }
            }
        }
        is Result.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "No histories found")
                }
            }
        }
        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "Error: ${(historiesState as Result.Error).exception.message}")
                }
            }
        }
    }
}