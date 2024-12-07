package com.example.safebox.features.patientactivity.home.presentation.ui.patient

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.safebox.features.fillprofile.domain.model.psychologist.Specialization
import com.example.safebox.features.patientactivity.dataobject.PatientScreensDO

/*
@Note
1. navigation when click the card (using navController)
2. remember the specialization
*/
@Composable
fun PsychologistCard(
    name: String,
    specializations: MutableList<Specialization>,
    imageUrl: String,
    psychologistId: String,
    navController: NavController
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )
    Card(
        modifier = Modifier
            .width(175.dp)
            .height(280.dp)
            .clickable{
                Log.d("CardClick", "Yes$psychologistId")
                navController.navigate(route = "${ PatientScreensDO.Consultation.screen}/detail/$psychologistId")
            },
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
                    painter = painter,
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
//                Text(
//                    text = "#$psychologistId",
//                    textAlign = TextAlign.Start,
//                    fontSize = 10.sp,
//                    color = Color.Black,
//                    fontWeight = FontWeight.ExtraBold,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = name,
                    textAlign = TextAlign.Start,
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Jumlah spesialisasi:\n${specializations.size}",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    lineHeight = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2
                )
            }
        }

    }
}