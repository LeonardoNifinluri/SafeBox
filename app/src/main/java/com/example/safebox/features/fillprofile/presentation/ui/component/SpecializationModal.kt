package com.example.safebox.features.fillprofile.presentation.ui.component


import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModel

@Composable
fun SpecializationModal(
    viewModel: PsychologistViewModel
) {

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
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF821131))
                    .padding(vertical = 18.dp, horizontal = 20.dp)
                    .heightIn(min = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Daftar Spesialisasi",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(viewModel.psychologistData.value.specializations){
                        specialization ->
                        SpecializationCard(specialization = specialization)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    item{
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = {
                                    //show specialization dialog
                                    viewModel.onRemoveSpecialization()
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(Color.White)
                            ) {
                                Text(
                                    text = "Hapus",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    //show specialization dialog
                                    viewModel.onAddSpecialization()
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(Color.White)
                            ) {
                                Text(
                                    text = "Tambah",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                //dismiss the modal
                                viewModel.dismissModal(field = 1)
                                Log.d("OnSaveSpecialization", viewModel.psychologistData.value.specializations.size.toString())
                            },
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
    }

    if(viewModel.showSpecializationDialog.value){
        Dialog(
            onDismissRequest = {
                viewModel.onCancelSpecialization()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize().padding(10.dp)
                ){
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .specializations
                            .last()
                            .field,
                        onValueChange = {
                            viewModel.onFieldChange(it)
//                            specializationTextFieldNotBlank[0] = it.isNotBlank()
                        },
                        label = {
                            Text(text = "Bidang")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .specializations
                            .last()
                            .description,
                        onValueChange = {
                            viewModel.onDescriptionChange(it)
//                            specializationTextFieldNotBlank[0] = it.isNotBlank()
                        },
                        label = {
                            Text(text = "Deskripsi Bidang")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Button(
                            onClick = {
                                //this is to cancel the addition
                                viewModel.onCancelSpecialization()
                            }
                        ) {
                            Text(text = "Batal")
                        }
                        Button(
                            onClick = {
                                //this is to cancel the addition
                                viewModel.onSaveSpecialization()
                            }
                        ) {
                            Text(text = "Simpan")
                        }
                    }
                }
            }
        }
    }

}