package com.example.safebox.features.fikri

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R


data class DetailDiary(
    var createdAt: String = "",
    var title: String = "",
    var content: String = "",
    var id: String = ""
)

@Composable
fun HeaderDiary(detailDiary: DetailDiary, onBackClick: () -> Unit) {
    Surface(
        color = Color(0xFF821131),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
        ){
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_back_3),
                    contentDescription = "Kembali",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = detailDiary.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    Icon(
                        painter = painterResource(id = R.drawable.logo_tanggal),
                        contentDescription = "logo tanggal",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = detailDiary.createdAt,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderDiary(){
    HeaderDiary(
        detailDiary = DetailDiary(
            id = "jsdgcgscusd",
            content = "sjkdhciusacsahdcsdlckjsdlcsdalkcshaklchs kaslshncdksdcklsdahkcasdklcsakcsadk jbasdcbsdackljsadcd",
            title = "Aku Digigit Ayangmu :v",
            createdAt = "Selasa, 29 Februari 2024"
        ), onBackClick = { println("Back Icon Clicked") }
    )
}

@Composable
fun ContentDiary(detailDiary: DetailDiary) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color(0xFFFABC3F)),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = detailDiary.content,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContentDiary(){
    ContentDiary(
        detailDiary = DetailDiary(
            id = "jsdgcgscusd",
            content = "sjkdhciusacsahdcsdlckjsdlcsdalkcshaklchs " +
                    "kaslshncdksdcklsdahkcasdklcsakcsadk jbasdcbsdackljsadcd",
            title = "Aku Digigit Ayangmu :v",
            createdAt = "Selasa, 29 Februari 2024"
        )
    )
}

@Composable
fun DetailDiaryScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFEBEBEB)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            LazyColumn {
                item {
                    HeaderDiary(
                        detailDiary = DetailDiary(
                            id = "jsdgcgscusd",
                            content = "sjkdhciusacsahdcsdlckjsdlcsdalkcshaklchs kaslshncdksdcklsdahkcasdklcsakcsadk jbasdcbsdackljsadcd",
                            title = "Aku Digigit Ayangmu :v",
                            createdAt = "Selasa, 29 Februari 2024"
                        ), onBackClick = { println("Back Icon Clicked") }
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .background(Color(0xFFEBEBEB))
                            .fillMaxWidth()
                    )
                }

                item {
                    ContentDiary(
                        detailDiary = DetailDiary(
                            id = "jsdgcgscusd",
                            content = "sjkdhciusacsahdcsdlckjsdlcsdalkcshaklchs " +
                                    "kaslshncdksdcklsdahkcasdklcsakcsadk jbasdcbsdackljsadcd",
                            title = "Aku Digigit Ayangmu :v",
                            createdAt = "Selasa, 29 Februari 2024"
                        )
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun PreviwDetailDiaryScreen(){
    DetailDiaryScreen()
}