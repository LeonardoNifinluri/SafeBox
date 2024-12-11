package com.example.safebox.features.patientactivity.history.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.core.result.Result
import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.presentation.viewmodel.HistoryScreenViewModel

@Composable
fun HistoryScreen(
    userId: String,
    navController: NavController,
    viewModel: HistoryScreenViewModel = viewModel()
) {
    val historiesState by viewModel.historiesState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllHistories(patientId = userId)
    }
    Surface(modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when(historiesState){
                is Result.Loading -> {
                    Text(text = "Loading All Histories")
                }
                is Result.Success -> {
                    val histories = (historiesState as Result.Success<List<History>>).data
                    LazyColumn {
                        items(histories){ history ->
                            Text(text = history.id)
                            Text(text = history.psychologistName)
                            Text(text = history.psychologistId)
                            Text(text = viewModel.getDayString(history.day))
                            Text(text = history.createdAt)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                is Result.Empty -> {
                    Text(text = "No histories found")
                }
                is Result.Error -> {
                    Text(text = "Error: ${(historiesState as Result.Error).exception.message}")
                }
            }
        }
    }
}