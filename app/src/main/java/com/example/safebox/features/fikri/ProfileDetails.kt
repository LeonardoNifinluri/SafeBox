package com.example.safebox.features.fikri

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.safebox.R
import com.google.firebase.annotations.concurrent.Background

@Composable
fun PhotoAndNameWithBackButton(
    name: String,
    specialization: String,
    @DrawableRes imageRes : Int,
    onBackClick:() -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Profil Image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(300.dp).background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 28.dp)
                .align(Alignment.BottomStart)
        ){
            Text(
                text = name,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = specialization,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            )
        }
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top=16.dp, start = 10.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart) // Sejajarkan tombol ke kiri
                    // Menghilangkan padding tambahan
            ) {
                Icon(
                    painter = painterResource(R.drawable.logo_back_3),
                    contentDescription = "logo kembali",
                    modifier = Modifier.size(22.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPhotoAndNameWithBackButton() {
    PhotoAndNameWithBackButton(
        name = "Fikri'09",
        specialization = "Depresi, Remaja, Anak",
        imageRes = R.drawable.gambar_testing3,
        onBackClick = { println("Kembali") }
    )
}

@Composable
fun InformationContact(
    workLocation: String,
    email: String,
    phoneNumber: String
){
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "Informasi Kontak",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Icon(
                            painter = painterResource(R.drawable.logo_lokasi),
                            contentDescription = "logo lokasi",
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = workLocation,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Icon(
                            painter = painterResource(R.drawable.logo_email),
                            contentDescription = "logo email",
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = email,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Icon(
                            painter = painterResource(R.drawable.logo_telephone),
                            contentDescription = "logo telephone",
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = phoneNumber,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInformationContact() {
    InformationContact(
        workLocation = "Makassar",
        email = "nurfikrimustapa@gmail.com",
        phoneNumber = "089745901043"
    )
}


//data dummy
data class Psikolog(
    var experiences: MutableList<Pengalaman> = mutableStateListOf(),
    var availability: List<Boolean> =  List(size = 7) { false }, //this list has length 7, idx 0 -> monday, idx 1 -> tuesday, etc. If true then available else not
)

data class Pengalaman(
    var years: Int = 0,
    var institution: String = "",
    var role: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var description: String = ""
)


val dummyExperience = listOf(
    Pengalaman(
        years = 1,
        institution = "Satu Persen",
        role = "Content Creator Psikologi",
        startDate = "Februari 2024",
        endDate = "Juli 2024",
        description = "Bertanggung jawab dalam membuat konten seputar psikologi, konseling, dan terapi "
    ),
    Pengalaman(
        years = 2,
        institution = "Halo Jiwa Indonesia",
        role = "Psikiater Anak",
        startDate = "Februari 2022",
        endDate = "Februari 2024",
        description = "Bertanggung jawab memberikan konseling dan terapi kepada anak-anak."
    ),
    Pengalaman(
        years = 1,
        institution = "Lembaga Psikologi Unhas",
        role = "Psikolog Klinis",
        startDate = "Februari 2023",
        endDate = "Februari 2024",
        description = "Melakukan konseling kepada civitas akademika Unhas."
    )
)
//end of data dummy

@Composable
fun ExperienceList(experiences: List<Pengalaman>) {
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp, bottom = 10.dp)
                ){
                    Text(
                        text = "Pengalaman",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 18.dp )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        experiences.forEach { experience ->
                            ExperienceItem(experience)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExperienceItem(experience: Pengalaman) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        border = BorderStroke(2.dp,Color(0xFFFABC3F)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                )
            {
                Text(
                    text = "${experience.role}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                )
                Text(
                    text = "${experience.years} Tahun",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = experience.institution,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${experience.startDate} - ${experience.endDate}",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Deskripsi: ${experience.description}",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExperienceList() {
    ExperienceList(experiences = dummyExperience)
}


@Composable
fun ButtonBookPsikolog(onClick: () -> Unit){
    val backgroundColor = Color(0xFFEBEBEB)
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, bottom = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF821131)),
                shape = RoundedCornerShape(10.dp)

            ) {
                Text(
                    text = "Pesan dan Tentukan Jadwal Konsultasi",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewButtonBookPsikolog(){
    ButtonBookPsikolog(onClick = {println("TESSS")})
}

@Composable
fun ModalContent(
    psikolog: Psikolog,
    onDismiss: () -> Unit,
    onDaySelected: (String) -> Unit
) {
    val daysOfWeek = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit){
                detectTapGestures(onPress = {})
            }
    ){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.Transparent),
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF821131))
                    .padding(vertical = 18.dp, horizontal = 20.dp)
                    .heightIn(min = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Jadwal Yang Tersedia",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 10.dp, bottom = 30.dp),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    daysOfWeek.forEachIndexed { index, day ->
                        if (psikolog.availability[index]) {
                            Button(
                                onClick = { onDaySelected(day) },
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(15.dp),// Menyesuaikan ukuran tombol
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFABC3F))
                            ) {
                                Text(
                                    text = day,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "Kembali",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}

@Composable
fun ProfileDetailsScreen(){
    var showModel by remember { mutableStateOf(false) }

    //this is psychologist dataclass
    val psikolog = Psikolog(
        availability = listOf(true, false, false, true, false, true, true)
    )

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                PhotoAndNameWithBackButton(
                    name = "Fikri'09", //psychologist name
                    specialization = "Depresi, Remaja, Anak", //psychologist specialization
                    imageRes = R.drawable.gambar_testing3, //psychologist image (using url)
                    onBackClick = { println("Kembali") } //on back click
                )
            }
            item {
                InformationContact(
                    workLocation = "Makassar", //psychologist work location
                    email = "nurfikrimustapa@gmail.com", //psychologsit email
                    phoneNumber = "089745901043" //psychologist phone number
                )
            }
            item{
                //this experience list should receive psychologist.experiences
                ExperienceList(experiences = dummyExperience)
            }
            item{
                //this is to choose day (should receive availability)
                ButtonBookPsikolog(onClick = {
                    showModel = true
                })
            }
        }
        if(showModel){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .align(Alignment.Center)
            ){
                ModalContent(
                    psikolog = psikolog,
                    onDismiss = {showModel = false},
                    onDaySelected = { selectedDay ->
                        // Mengelola pemilihan hari
                        println("Hari terpilih: $selectedDay")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileDetailsScreen(){
    ProfileDetailsScreen()
}


