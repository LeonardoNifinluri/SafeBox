package com.example.safebox.features.fikri

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun HeaderKonsultasiPsikolog(
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
                        text = "Konsul Psikolog",
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
                        text = "Yuk ceritakan keluh kesah mu dengan psikolog !",
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
fun PreviewHeaderKonsultasiPsikolog() {
    HeaderKonsultasiPsikolog(onBackClick = { println("Kembali") })
}


@Composable
fun PsychologistListSectionKonsul(onCardClick: (String) -> Unit){
    val backgroundColor = Color(0xFFEBEBEB)
    val psychologistList = listOf(
        Psychologist("Leo Koffte", "Depresi, Keluarga, Remaja", R.drawable.gambar_testing),
        Psychologist("Fikri’09", "Depresi, Remaja, Anak", R.drawable.gambar_testing2),
        Psychologist("Dorisman IF", "Anxiety, Remaja, Dewasa", R.drawable.gambar_testing3),
        Psychologist("Leo Koffte", "Depresi, Keluarga, Remaja", R.drawable.gambar_testing),
        Psychologist("Fikri’09", "Depresi, Remaja, Anak", R.drawable.gambar_testing2),
        Psychologist("Dorisman IF", "Anxiety, Remaja, Dewasa", R.drawable.gambar_testing3),
        Psychologist("Dorisman IF", "Anxiety, Remaja, Dewasa", R.drawable.gambar_testing3),
        Psychologist("Fikri’09", "Depresi, Remaja, Anak", R.drawable.gambar_testing2)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            PsychologistList(psychologists = psychologistList, onCardClick = onCardClick)
        }
    }
}



@Composable
fun KonsulPsikologScreen(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            HeaderKonsultasiPsikolog(onBackClick = { println("Kembali") })
        }
        item {
            PsychologistListSectionKonsul(
                onCardClick = { selectedName ->
                    println("Selected psychologist: $selectedName")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewKonsulPsikologScreen() {
    KonsulPsikologScreen()
}