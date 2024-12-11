package com.example.safebox.features.fillprofile.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.safebox.R
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.data.repository.FirebaseImageRepository
import com.example.safebox.features.fillprofile.data.repository.FirebaseRepository
import com.example.safebox.features.fillprofile.domain.usecase.SavePsychologistDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import com.example.safebox.features.fillprofile.presentation.ui.component.AvailabilityModal
import com.example.safebox.features.fillprofile.presentation.ui.component.ExperienceModal
import com.example.safebox.features.fillprofile.presentation.ui.component.SpecializationModal
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModel
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModelFactory
import com.example.safebox.ui.theme.MainColor

/*
* need to be added : remove added specialization and experience
* */

@Composable
fun PsychologistFillProfileScreen(navController: NavController, userId: String, email: String) {
    val uploadImageUseCase = UploadImageUseCase(FirebaseImageRepository())
    val savePsychologistDataUseCase = SavePsychologistDataUseCase(FirebaseRepository())

    val factory = PsychologistViewModelFactory(
        userId = userId,
        email = email,
        uploadImageUseCase = uploadImageUseCase,
        savePsychologistDataUseCase = savePsychologistDataUseCase
    )
    val context = LocalContext.current

    val viewModel: PsychologistViewModel = ViewModelProvider(context as ComponentActivity, factory)[PsychologistViewModel::class.java]

    val psychologistData = viewModel.psychologistData

    //this is to pick image from gallery
    //don't make any changes from this
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri: Uri? ->
        uri?.let{
            viewModel.onImageChange(it)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = MainColor)
    ){
        LazyColumn {
            item{
                Column (
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(95.dp))
                    Text(
                        text = "Profil Psikolog",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    //this is for name
                    OutlinedTextField(
                        value = psychologistData.value.name,
                        onValueChange = { viewModel.onNameChange(it) },
                        label = {
                            Text(text = stringResource(id = R.string.user_name))
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray
                        )
                    )
                    //this is for work location
                    OutlinedTextField(
                        value = psychologistData.value.workLocation,
                        onValueChange = { viewModel.onWorkLocationChange(it) },
                        label = {
                            Text(text = "Lokasi Kerja")
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray
                        )
                    )
                    //this is for phone number
                    OutlinedTextField(
                        value = psychologistData.value.phoneNumber,
                        onValueChange = { viewModel.onPhoneNumberChange(it) },
                        label = {
                            Text(text = "No. Telp")
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_email),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Button(
                        onClick = {
                            viewModel.showModal(field = 0)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ) {
                        Text(text = "Pilih Hari Kerja")
                    }
                    Button(
                        onClick = {
                            viewModel.showModal(field = 1)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ) {
                        Text(text = "Isi Spesialisasi")
                    }
                    Button(
                        onClick = {
                            viewModel.showModal(field = 2)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ) {
                        Text(text = "Isi Pengalaman Kerja")
                    }
                    Button(
                        onClick = {
                            launcher.launch(input = "image/*")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ) {
                        Text(text = "Pilih Foto Profil")
                    }
                }
            }
            item{
                Column (
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    if(viewModel.imageUri.value != null){
                        AsyncImage(
                            model = viewModel.imageUri.value,
                            contentDescription = null,
                            modifier = Modifier.padding(10.dp)
                        )

                    }
                }
            }
            item{
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(
                        onClick = {
                            //this is will save to firebase realtime database
                            viewModel.onConfirmSubmit {
                                Log.d("OnConfirmSubmit-FillProfileScreen", "success")
                                navController.navigate(route = "HomeScreen/${Role.PSYCHOLOGIST.name}/$userId"){
                                    popUpTo(route = "FillProfileScreen/$userId/${Role.PSYCHOLOGIST.name}/$email"){
                                        inclusive = true
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                        enabled = psychologistData.value.name.isNotEmpty() &&
                                psychologistData.value.workLocation.isNotEmpty() &&
                                psychologistData.value.phoneNumber.isNotEmpty() &&
                                psychologistData.value.availability.contains(true) &&
                                psychologistData.value.specializations.isNotEmpty() &&
                                psychologistData.value.experiences.isNotEmpty() &&
                                viewModel.imageUri.value != null &&
                                !viewModel.isLoading.value
                    ) {
                        Text(text = if(viewModel.isLoading.value) "Confirming..." else "Confirm")
                    }
                }
            }
        }

        if(viewModel.showAvailabilityModal.value){
            AvailabilityModal(
                availability = psychologistData.value.availability,
                onSelectedDay = { index ->
                    viewModel.onAvailabilityChange(index)
                },
                onDismiss = {
                    viewModel.dismissModal(field = 0)
                    Log.d("AvailabilityStatus", psychologistData.value.availability.toString())
                }
            )
        }
        if(viewModel.showSpecializationModal.value){
            SpecializationModal(viewModel = viewModel)
        }
        if(viewModel.showExperienceModal.value){
            ExperienceModal(viewModel = viewModel)
        }
    }
}