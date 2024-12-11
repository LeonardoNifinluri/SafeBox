package com.example.safebox.features.patientactivity.diary.presentation.form.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO
import com.example.safebox.features.patientactivity.diary.presentation.form.ui.component.DiaryForm
import com.example.safebox.features.patientactivity.diary.presentation.form.ui.component.Header
import com.example.safebox.features.patientactivity.diary.presentation.form.viewmodel.CreateDiaryViewModel

@Composable
fun CreateDiaryScreen(
    navController: NavController,
    userId: String,
    viewModel: CreateDiaryViewModel = viewModel()
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            item {
                Header(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
            item{
                DiaryForm(
                    diaryState = viewModel.diary,
                    onTitleChange = { title ->
                        viewModel.onTitleChange(title)
                    },
                    onContentChange = { content ->
                        viewModel.onContentChange(content)
                    },
                    onCreateDiary = {
                        viewModel.createDiary(
                            userId = userId,
                            onSuccess = {
                                navController.navigate(route = PatientScreensDO.Note.screen){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                }
                            },
                            onFail = {
                                Toast.makeText(
                                    context,
                                    "Gagal membuat diary",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                )
            }
        }
    }
}