package com.example.safebox.features.patientactivity.diary.presentation.form.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.patientactivity.diary.presentation.form.viewmodel.CreateDiaryViewModel

@Composable
fun CreateDiaryScreen(
    navController: NavController,
    userId: String,
    viewModel: CreateDiaryViewModel = viewModel()
) {
    val diary = viewModel.diary.value
    val context = LocalContext.current
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            OutlinedTextField(
                value = diary.title,
                onValueChange = {
                    viewModel.onTitleChange(it)
                },
                label = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = diary.content,
                onValueChange = {
                    viewModel.onContentChange(it)
                },
                label = {
                    Text(text = "Content")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.createDiary(
                        userId = userId,
                        onSuccess = {
                            navController.popBackStack()
                        },
                        onFail = {
                            Toast.makeText(
                                context,
                                "Fail to create diary",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                },
                enabled = diary.title.isNotBlank() && diary.content.isNotBlank() && !viewModel.isLoading.value
            ) {
                Text(text = if(viewModel.isLoading.value) "Creating Diary..." else "Create Diary")
            }
        }
    }
}