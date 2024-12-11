package com.example.safebox.features.patientactivity.diary.presentation.form.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.features.patientactivity.diary.domain.model.Diary

@Composable
fun DiaryForm(
    diaryState: State<Diary>,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onCreateDiary: () -> Unit
) {
    var titleHeight by remember { mutableStateOf(50.dp) }
    var contentHeight by remember { mutableStateOf(440.dp) }

    val backgroundColor = Color(0xFFEBEBEB)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column (
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Spacer(modifier = Modifier.height(3.dp))
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp)
                    ) {
                        Text(
                            text = "Judul Diarymu",
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 10.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                        OutlinedTextField(
                            value = diaryState.value.title,
                            onValueChange = {
                                onTitleChange(it)
                                titleHeight = (50 + it.length*2).dp
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp, max = titleHeight),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = false
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "Isi Diarymu",
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 5.dp),
                            fontWeight = FontWeight.SemiBold
                        )
                        OutlinedTextField(
                            value = diaryState.value.content,
                            onValueChange = {
                                onContentChange(it)
                                contentHeight = (440 + it.length*2).dp
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 440.dp, max = contentHeight),
                            singleLine = false,
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(23.dp))
            }
            Column {
                Button(
                    onClick = {
                        onCreateDiary()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF821131)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Simpan Diary",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
//            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}