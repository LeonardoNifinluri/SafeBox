package com.example.safebox.features.fikri

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safebox.R

@Composable
fun HeaderHomepageSection(){
    Surface(
        color = Color(0xFFFABC3F)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp, start = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Column {
                    Text(
                        text = "Halo, Leo",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp,
                        letterSpacing = (0).sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Semoga harimu penuh kebahagiaan.\nIngat, kami selalu di sini, menemanimu kapan saja.",
                        fontWeight = FontWeight.Normal,
                        lineHeight = 14.sp,
                        fontSize = 10.sp,
                        letterSpacing = (0).sp
                    )
                }

                //this is for image profile
                Image(
                    painter = (painterResource(R.drawable.frame_11__1_)),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(74.dp)
                        .clip(CircleShape),
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderHomepageSection() {
    MaterialTheme {
        HeaderHomepageSection()
    }
}

@Composable
fun FeatureButton(@DrawableRes iconRes: Int, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(42.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center,
            color = Color.Black,
            lineHeight = 15.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeatureButton() {
    FeatureButton(
        iconRes = R.drawable.ic_email,
        text = "Contoh Teks",
        onClick = { }
    )
}

@Composable
fun FeatureButtons() {
    val backgroundColor = Color(0xFFFABC3F)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureButton(
                        iconRes = R.drawable.logo_konsul_psikolog,
                        text = "Konsul\nPsikolog",
                        onClick = { }
                    )
                    FeatureButton(
                        iconRes = R.drawable.logo_diary_keseharianku,
                        text = "Diary\nKeseharianku",
                        onClick = { }
                    )
                    FeatureButton(
                        iconRes = R.drawable.logo_status_mentalku,
                        text = "Status\nMentalku",
                        onClick = { }
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeatureButtons() {
    FeatureButtons()
}

@Composable
fun MoodSlider(){
    var moodValue by remember {mutableFloatStateOf(0f)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF821131)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bagaimana Kondisi Mental\nKamu Hari Ini ? ",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 23.sp,
            modifier = Modifier.padding(top = 35.dp, bottom = 15.dp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Slider(
                value = moodValue,
                onValueChange = {moodValue = it},
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = Color.Yellow,
                    inactiveTickColor = Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Icon(
                painter = painterResource(R.drawable.logo_smile_slider_pilihan2),
                contentDescription = "Emoji Senyum",
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (moodValue * 3.25).dp),
                tint = Color.White
            )

        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Preview
@Composable
fun TestSlider() {
    MaterialTheme{
        MoodSlider()
    }
}


@Composable
fun PsychologistCard(name: String, specialization: String, @DrawableRes imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(175.dp)
            .height(270.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp)
                )
            }

            Column(
                modifier = Modifier.padding(vertical = 14.dp, horizontal = 14.dp)
            ){
                Text(
                    text = name,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Spesialisasi:\n$specialization",
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPsychologistCard() {
    PsychologistCard("Leo Koffte", "Depresi, Keluarga, Remaja ", R.drawable.gambar_testing){
        //this is clickable card
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPsychologistCard2() {
    PsychologistCard("Fikri’09", "Depresi, Remaja, Anak ", R.drawable.gambar_testing2){
        //this is clickable card
    }
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 18.dp, top = 20.dp, bottom = 10.dp)
    )
}

@Composable
fun PsychologistList(psychologists: List<Psychologist>, onCardClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        psychologists.chunked(2).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                chunk.forEach { psychologist ->
                    Box(
                        modifier = Modifier
                            .weight(1f, fill = true),
                        contentAlignment = Alignment.Center
                    ) {
                        PsychologistCard(
                            name = psychologist.name,
                            specialization = psychologist.specialization,
                            imageRes = psychologist.imageRes,
                            onClick = { onCardClick(psychologist.name) }
                        )
                    }
                }
                if (chunk.size == 1) {
                    Spacer(modifier = Modifier.weight(1f)) // Mengisi ruang kosong jika hanya 1 item
                }
            }
        }
    }
}


@Composable
fun SeeMoreButton(onClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFABC3F)),
            shape = RoundedCornerShape(8.dp)

            ) {
            Text(
                text = "Lihat Selengkapnya...",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}


@Composable
fun PsychologistSectionList(onCardClick: (String) -> Unit, onSeeMoreClick: () -> Unit) {
    val backgroundColor = Color(0xFFEBEBEB)
    val psychologistList = listOf(
        Psychologist("Leo Koffte", "Depresi, Keluarga, Remaja", R.drawable.gambar_testing),
        Psychologist("Fikri’09", "Depresi, Remaja, Anak", R.drawable.gambar_testing2),
        Psychologist("Dorisman IF", "Anxiety, Remaja, Dewasa", R.drawable.gambar_testing3),
        Psychologist("Leo Koffte", "Depresi, Keluarga, Remaja", R.drawable.gambar_testing),
        Psychologist("Fikri’09", "Depresi, Remaja, Anak", R.drawable.gambar_testing2),
        Psychologist("Dorisman IF", "Anxiety, Remaja, Dewasa", R.drawable.gambar_testing3)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxWidth() //changed from fillmaxsize to fillmaxwidth
        ) {
            SectionTitle(title = "Konsul ke Psikolog Yuk!")
            PsychologistList(psychologists = psychologistList, onCardClick = onCardClick)
            SeeMoreButton(onClick = onSeeMoreClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPsychologistSectionList() {
    PsychologistSectionList(
        onCardClick = { selectedName ->
            println("Card clicked: $selectedName")
        },
        onSeeMoreClick = {
            println("See More button clicked")
        }
    )
}



@Composable
fun NavItem(
    @DrawableRes iconRes: Int,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Light,
                color = Color.White
            ),
            textAlign = TextAlign.Center,
            lineHeight = 9.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNavItem() {
    NavItem(
        iconRes = R.drawable.logo_beranda,
        text = "Beranda",
        isSelected = false,
        onClick = {}
    )
}

@Composable
fun BottomNavBar(
    selectedRoute: String,
    onNavItemClick: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("Beranda", R.drawable.logo_beranda, "beranda"),
        BottomNavItem("Konsul\nPsikolog", R.drawable.logo_konsulpsikolog, "route"),
        BottomNavItem("Diary\nKeseharianku", R.drawable.logo_diarykeseharianku, "diary"),
        BottomNavItem("Status\nMentalku", R.drawable.logo_statusmentalku, "status"),
        BottomNavItem(" Profil ", R.drawable.logo_profil, "profil")
    )

    Surface(
        color = Color(0xFFC7253E),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(vertical = 13.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(-0.6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEach { item ->
                    NavItem(
                        iconRes = item.icon,
                        text = item.title,
                        isSelected = item.route == selectedRoute,
                        onClick = { onNavItemClick(item.route) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    BottomNavBar(
        selectedRoute = "profil", //route yuang dipilih
        onNavItemClick = { route ->
            println("Navigasi ke: $route") // Simulasikan navigasi pada logcat
        }
    )
}

@Composable
fun HomepageScreen(
    selectedRoute: String,
    onNavItemClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedRoute = selectedRoute,
                onNavItemClick = onNavItemClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Padding untuk menghindari overlap dengan navbar
        ) {
            item {
                HeaderHomepageSection()
            }
            item {
                FeatureButtons()
            }
            item {
                MoodSlider()
            }
            item {
                PsychologistSectionList(
                    onCardClick = { selectedName ->
                        println("Selected psychologist: $selectedName")
                    },
                    onSeeMoreClick = {
                        println("See more button clicked!")
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomepageScreen() {
    HomepageScreen(
        selectedRoute = "profil",
        onNavItemClick = { println("Navigasi ke: $it") }
    )
}