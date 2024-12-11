package com.example.safebox.features.fikri

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun HeaderRiwayatPesanPsikolog(
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
                        text = "Riwayat Konsultasi",
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
                        text = "Kamu aman bercerita dengan orang-orang ini",
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
fun PreviewHeaderRiwayatPesanPsikolog() {
    HeaderRiwayatPesanPsikolog(onBackClick = { println("Kembali") })
}

data class PesanPsikolog(
    var id: String = "",
    var psikologName: String = "",
    var pasienName: String = "",
    var consultasionDay: String = ""
)

@Composable
fun RiwayatKonsultasiCard(
    pesanPsikolog: PesanPsikolog
) {
    Surface(
        color = Color(0xFFEBEBEB)

    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 8.dp)
        ){
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color(0xFF821131))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ){
                        Column {
                            Text(
                                text = "Psikolog",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = pesanPsikolog.psikologName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Text(
                            text = "Jadwal Konsultasi",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Konsultasi di Hari",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = pesanPsikolog.consultasionDay,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                        }
                        Icon(
                            painter = painterResource(R.drawable.logo_riwayatkonsultasi),
                            contentDescription = "logo riwayat konsultasi",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatKonsultasiCard(){
    RiwayatKonsultasiCard(
        pesanPsikolog = PesanPsikolog(
            id = "1",
            psikologName = "Fikri'09",
            pasienName = "CRTEATOR-09",
            consultasionDay = "Rabu"
        )
    )
}

@Composable
fun RiwayatKonsultasiScreen() {
    val dummyDataList = listOf(
        PesanPsikolog(
            id = "1",
            psikologName = "Leo'09",
            pasienName = "CREATOR-09",
            consultasionDay = "Rabu"
        ),
        PesanPsikolog(
            id = "2",
            psikologName = "Rafathar'09",
            pasienName = "CREATOR-09",
            consultasionDay = "Kamis"
        ),
        PesanPsikolog(
            id = "3",
            psikologName = "Destroyer'09",
            pasienName = "CREATOR-09",
            consultasionDay = "Jumaat"
        ),
        PesanPsikolog(
            id = "4",
            psikologName = "Ismail Bin Mail",
            pasienName = "CREATOR-09",
            consultasionDay = "Sabtu"
        ),
        PesanPsikolog(
            id = "5",
            psikologName = "Rika Sartika",
            pasienName = "CREATOR-09",
            consultasionDay = "Jumaat"
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            HeaderRiwayatPesanPsikolog(onBackClick = { println("Kembali") })
        }
        item {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .background(Color(0xFFEBEBEB))
                    .fillMaxWidth()
            )
        }
        items(dummyDataList) { riwayat ->
            RiwayatKonsultasiCard(pesanPsikolog = riwayat)
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

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatKonsultasiScreen() {
    RiwayatKonsultasiScreen()
}