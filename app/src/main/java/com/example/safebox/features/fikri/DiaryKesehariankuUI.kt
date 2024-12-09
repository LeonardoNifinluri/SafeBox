package com.example.safebox.features.fikri

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

@Composable
fun AddDiaryCard(
    onClick: () -> Unit
) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .padding(top = 22.dp, start = 20.dp, end = 20.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(10.dp)).background(Color(0xFFBEBEBEB))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(130.dp) // Sesuaikan tinggi sesuai kebutuhan
                    .background(Color(0xFF821131)) // Warna latar belakang merah
                    .clickable(onClick = onClick)
                    .padding(5.dp)

            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    val dashWidth = 35f
                    val dashGap = 15f
                    val strokeWidth = 7f

                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)

                    drawRoundRect(
                        color = Color.Gray,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(20f, 20f),
                        style = Stroke(width = strokeWidth, pathEffect = pathEffect)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Menambahkan padding pada konten
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Tambah\nDiary Keseharianku",
                        color = Color.White,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(65.dp))
                    Icon(
                        painter = painterResource(R.drawable.logo_tambahdiary),
                        contentDescription = "Logo Tambah Diary",
                        tint = Color.White,
                        modifier = Modifier.size(38.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddDiaryCard(){
    AddDiaryCard(onClick = {"testuing..."})
}



data class Diary(
    val createdAt: String,
    val title: String
)

@Composable
fun DiaryCard(diary: Diary, onClick: (String) -> Unit = {}) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = backgroundColor
    ) {
        Card(
            modifier = Modifier
                .padding(top = 12.dp, start = 18.dp, end = 18.dp, bottom = 12.dp).clickable { onClick(diary.title) },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.logo_notes),
                        contentDescription = "Logo Note",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = diary.createdAt,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = diary.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiaryCard() {
    val dummyDataList = listOf(
        Diary(
            createdAt = "Senin, 27 Januari 2024",
            title = "Hari ini aku digigit ular guys :("
        ),
        Diary(
            createdAt = "Selasa, 28 Januari 2024",
            title = "Hari ini belajar Jetpack Compose!"
        ),
        Diary(
            createdAt = "Rabu, 29 Januari 2024",
            title = "Cuaca hari ini sangat cerah."
        ),
        Diary(
            createdAt = "Rabu, 30 Januari 2024",
            title = "Hari ini lagi pracet di Identitas Unhas Guys, Seru Bangett!!!!"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(dummyDataList) { diary ->
            DiaryCard(
                diary = diary,
                onClick = { title -> println("Clicked: $title") }
            )
        }
    }
}


@Composable
fun HeaderDiaryKesehariasnku(
    onBackClick: () -> Unit
) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                color = Color(0xFFFABC3F), // Menetapkan warna latar belakang
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp)// Pastikan Surface mengisi lebar layar
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
                        text = "Diary Keseharianku",
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
                        text = "Yuk ceritakan keseharian kamu disini",
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

@Preview
@Composable
fun PreviewHeaderDiaryKesehariasnku(){
    HeaderDiaryKesehariasnku(onBackClick = {println("Kembali")})
}

@Composable
fun DiaryKesehariankuScreen() {
    val dummyDataList = listOf(
        Diary(
            createdAt = "Senin, 27 Januari 2024",
            title = "Hari ini aku digigit ular guys :("
        ),
        Diary(
            createdAt = "Selasa, 28 Januari 2024",
            title = "Hari ini belajar Jetpack Compose!"
        ),
        Diary(
            createdAt = "Rabu, 29 Januari 2024",
            title = "Cuaca hari ini sangat cerah."
        ),
        Diary(
            createdAt = "Rabu, 30 Januari 2024",
            title = "Hari ini lagi pracet di Identitas Unhas Guys, Seru Bangett Asli Keren Banget Gilaa!!!!!"
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HeaderDiaryKesehariasnku(onBackClick = { println("kembali") })
        }
        item {
            AddDiaryCard(onClick = { println("Tambah Diary") })
        }
        items(dummyDataList) { diary ->
            DiaryCard(
                diary = diary,
                onClick = {title -> println("clicked: $title") }
            )
        }
        item {
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .background(Color(0xFFEBEBEB))
                    .fillMaxWidth()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiaryKesehariankuScreen() {
    DiaryKesehariankuScreen()
}
