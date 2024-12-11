package com.example.safebox.features.fikri

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R


@Composable
fun HeaderFormDiaryKeseharianku(
    onBackClick: () -> Unit
) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                color = Color(0xFFFABC3F),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally, // Mengubah alignment ke Start untuk memastikan elemen sejajar kiri
                    verticalArrangement = Arrangement.Center // Agar semua elemen berada di atas
                ) {
                    // Tombol Kembali
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .align(Alignment.Start) // Sejajarkan tombol ke kiri
                            .padding(start = 2.dp)// Menghilangkan padding tambahan
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.logo_back_3),
                            contentDescription = "logo kembali",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = "Tulis Diarymu Disini!",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()// Teks diselaraskan ke kiri
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    // Teks tambahan
                    Text(
                        text = "Jangan malu, jangan ragu, kamu aman disini <3",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 5.dp)
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderFormDiaryKeseharianku() {
    HeaderFormDiaryKeseharianku(onBackClick = { println("Kembali") })
}

data class DiaryTest(
    var createdAt: String = "",
    var title: String = "",
    var content: String = "",
    var id: String = ""
)

@Composable
fun FormDiaryKeseharianku(onSave: (DiaryTest) -> Unit){
    var titleForm by remember { mutableStateOf("") }
    var contentForm by remember { mutableStateOf("") }

    var titleHeight by remember { mutableStateOf(50.dp) }
    var contentHeight by remember { mutableStateOf(440.dp) }


    val backgroundColor = Color(0xFFEBEBEB)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
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
                            value = titleForm,
                            onValueChange = {
                                titleForm = it
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
                            value = contentForm,
                            onValueChange = {
                                contentForm = it
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
                        val diary = DiaryTest(title = titleForm, content = contentForm)
                        onSave(diary)
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
            Spacer(modifier = Modifier.height(6.dp))
        }

    }
}

@Composable
fun FormDiaryKesehatanScreen() {
    var savedDiary by remember { mutableStateOf(DiaryTest()) }

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {
                HeaderFormDiaryKeseharianku(onBackClick = { println("Kembali") })
            }
            item {
                FormDiaryKeseharianku { diary ->
                    savedDiary = diary
                    println("Diary Tersimpan: $savedDiary")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormDiaryKesehatanScreen() {
    FormDiaryKesehatanScreen()
}