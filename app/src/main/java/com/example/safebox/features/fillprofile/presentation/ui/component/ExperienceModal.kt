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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModel
import com.example.safebox.features.patientactivity.consultation.presentation.ui.component.ExperienceCard

@Composable
fun ExperienceModal(viewModel: PsychologistViewModel) {

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
                    text = "Daftar Pengalaman",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(viewModel.psychologistData.value.experiences){
                        ExperienceCard(experience = it)
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
                                    viewModel.onRemoveExperience()
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
                                    viewModel.onAddExperience()
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
                                viewModel.dismissModal(field = 2)
                                Log.d("onSaveExperience", viewModel.psychologistData.value.experiences.size.toString())
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

    if(viewModel.showExpDialog.value){
        Dialog(
            onDismissRequest = {
                viewModel.onCancelExperience()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                Column (
                    modifier = Modifier.fillMaxSize().padding(10.dp)
                ){
                    //this is years
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .institution,
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "Institution", data = it)
                        },
                        label = {
                            Text(text = "Institution")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )
                    //this is institution
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .years
                            .toString(),
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "Years", data = it)
                        },
                        label = {
                            Text(text = "Years")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    //this is role or work as
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .role,
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "Role", data = it)
                        },
                        label = {
                            Text(text = "Role")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    //this is start date
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .startDate,
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "StartDate", data = it)
                        },
                        label = {
                            Text(text = "Start Date (DD/MM/YYYY)")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    //this is end date
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .endDate,
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "EndDate", data = it)
                        },
                        label = {
                            Text(text = "End Date (DD/MM/YYYY)")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    //this is description
                    OutlinedTextField(
                        value = viewModel.psychologistData
                            .value
                            .experiences
                            .last()
                            .description,
                        onValueChange = {
                            viewModel.onExpDataChange(attribute = "Description", data = it)
                        },
                        label = {
                            Text(text = "Description")
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
                                viewModel.onCancelExperience()
                            }
                        ) {
                            Text(text = "Cancel")
                        }

                        Button(
                            onClick = {
                                viewModel.onSaveExperience()
                            },
                        ) {
                            Text(text = "Save")
                        }
                    }
                }

            }
        }
    }
}