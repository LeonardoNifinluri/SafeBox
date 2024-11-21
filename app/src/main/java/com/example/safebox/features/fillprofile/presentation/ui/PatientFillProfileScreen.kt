package com.example.safebox.features.fillprofile.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.data.repository.FirebaseImageRepository
import com.example.safebox.features.fillprofile.data.repository.FirebaseRepository
import com.example.safebox.features.fillprofile.domain.model.patient.Gender
import com.example.safebox.features.fillprofile.domain.usecase.SavePatientDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import com.example.safebox.features.fillprofile.presentation.viewmodel.PatientViewModel
import com.example.safebox.features.fillprofile.presentation.viewmodel.PatientViewModelFactory

@Composable
fun PatientEditProfileScreen(navController: NavController, userId: String, email: String) {
    val uploadImageUseCase = UploadImageUseCase(FirebaseImageRepository())
    val savePatientDataUseCase = SavePatientDataUseCase(FirebaseRepository())
    val factory = PatientViewModelFactory(
        userId = userId,
        email = email,
        uploadImageUseCase = uploadImageUseCase,
        savePatientDataUseCase = savePatientDataUseCase
    )
    val context = LocalContext.current

    val viewModel: PatientViewModel = ViewModelProvider(context as ComponentActivity, factory)[PatientViewModel::class.java]
    val patientData = viewModel.patientData.value

    //this is for launch image picker, don't make any changes of this
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri: Uri? ->
        uri?.let{
            viewModel.onProfileImageChange(it)
            Log.d(
                "ImageChanges",
                "Yes"
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Patient Profile",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
            )

            OutlinedTextField(
                value = patientData.name,
                onValueChange = { viewModel.onNameChange(it) },
                label = {
                    Text(text = "Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            OutlinedTextField(
                value = patientData.birthdate,
                onValueChange = { viewModel.onBirthdateChange(it) },
                label = {
                    Text(text = "Birthdate(DD/MM/YYYY)")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            OutlinedTextField(
                value = patientData.address,
                onValueChange = { viewModel.onAddressChange(it) },
                label = {
                    Text(text = "Address")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            OutlinedTextField(
                value = patientData.phoneNumber,
                onValueChange = { viewModel.onPhoneChange(it) },
                label = {
                    Text(text = "Phone Number")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            var isChecked1 by remember { mutableStateOf(false) }
            var isChecked2 by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = isChecked1,
                    onCheckedChange = {
                        isChecked1 = it
                        isChecked2 = !it
                        viewModel.onGenderChange(Gender.MALE)
                    }
                )
                Text(
                    text = "Male",
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.width(30.dp))

                Checkbox(
                    checked = isChecked2,
                    onCheckedChange = {
                        isChecked2 = it
                        isChecked1 = !it
                        viewModel.onGenderChange(Gender.FEMALE)
                    }
                )
                Text(
                    text = "Female",
                    textAlign = TextAlign.Start
                )
            }

            Button(
                onClick = {
                    launcher.launch(input = "image/*")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Text(text = "Pick Image")
            }

            if(viewModel.imageUri.value != null){
                AsyncImage(
                    model = viewModel.imageUri.value,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(50.dp)
                )

            }

            Button(
                onClick = {
                    //save data into firebase realtime database using : userId
                    //if success, navigate to patient homepage
                    try{
                        viewModel.onConfirmSubmit{
                            Log.d("SaveSuccess", "Navigate to patient home")
                            //make sure if the user press back button, the app closes
                            navController.navigate(route = "HomeScreen/${Role.PATIENT.name}/$userId"){
                                popUpTo(route = "FillProfileScreen/$userId/${Role.PATIENT.name}/$email"){
                                    inclusive = true
                                }
                            }
                        }
                    }catch(e: Exception){
                        Log.d("Exception When Upload", e.message!!)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = patientData.name.isNotBlank() &&
                        patientData.birthdate.isNotBlank() &&
                        patientData.address.isNotBlank() &&
                        patientData.phoneNumber.isNotBlank() &&
                        patientData.gender != Gender.NOT_SET &&
                        viewModel.imageUri.value != null &&
                        !viewModel.isLoading.value
            ) {
                Text(text = if(viewModel.isLoading.value) "Confirming..." else "Confirm")
            }

        }
    }

}
