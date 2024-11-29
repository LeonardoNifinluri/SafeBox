package com.example.safebox.features.fillprofile.presentation.ui

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.safebox.ui.theme.MainColor
import com.example.safebox.R
import com.yalantis.ucrop.UCrop
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
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
    val cacheDir = context.cacheDir

    // Handle UCrop result
    val uCropLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val croppedUri = UCrop.getOutput(result.data!!)
            croppedUri?.let {
                viewModel.onProfileImageChange(it) // Update the ViewModel with the cropped image URI
                Log.d("updateProfile", "Update profile ${viewModel.imageUri.value.toString()}")
            }
        }
    }

    //this is for launch image picker, don't make any changes of this
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try{
                // Generate a destination URI for the cropped image
                val randomFileName = "croppedImage_${System.currentTimeMillis()}.jpg"
                val croppedUri = Uri.fromFile(File(cacheDir, randomFileName))

                // Configure UCrop
                val uCropIntent = UCrop.of(uri, croppedUri)
                    .withAspectRatio(1f, 1f) // Aspect ratio 1:1
                    .withMaxResultSize(100, 100) // Max size in pixels
                    .getIntent(context)

                // Launch UCrop using uCropLauncher
                uCropLauncher.launch(uCropIntent)
            }catch(e: Exception){
                Log.e("ImagePicker", "Error creating UCrop intent: ${e.message}")
                Toast.makeText(context, "Error processing image ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Log.e("ImagePicker", "Invalid URI")
            Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = MainColor)
    ){
        LazyColumn{
            item{
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(95.dp))
                    Text(
                        text = stringResource(id = R.string.profile) + ' ' + stringResource(id = R.string.patient),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = patientData.name,
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Row{
                        Button(
                            onClick = {
                                viewModel.onPickBirthdate()
                            }
                        ){
                            Text(text = stringResource(R.string.birthdate))
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = patientData.birthdate)
                    }
                    if(viewModel.showDateDialog.value){
                        val datePickerState = rememberDatePickerState()
                        DatePickerDialog(
                            onDismissRequest = {
                                viewModel.onCancelPickDate()
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    viewModel.onBirthdateChange(viewModel.convertMillisToDate(datePickerState.selectedDateMillis!!))
                                }) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    viewModel.onCancelPickDate()
                                }) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = patientData.address,
                        onValueChange = { viewModel.onAddressChange(it) },
                        label = {
                            Text(text = stringResource(R.string.address))
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
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = patientData.phoneNumber,
                        onValueChange = { viewModel.onPhoneChange(it) },
                        label = {
                            Text(text = stringResource(R.string.phone_number))
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
                            },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = Color.Gray,
                                checkedColor = Color.LightGray
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.gender_male),
                            textAlign = TextAlign.Start
                        )

                        Spacer(modifier = Modifier.width(30.dp))

                        Checkbox(
                            checked = isChecked2,
                            onCheckedChange = {
                                isChecked2 = it
                                isChecked1 = !it
                                viewModel.onGenderChange(Gender.FEMALE)
                            },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = Color.Gray,
                                checkedColor = Color.LightGray
                            )
                        )
                        Text(
                            text = stringResource(R.string.gender_female),
                            textAlign = TextAlign.Start
                        )
                    }

                    Button(
                        onClick = {
                            launcher.launch(input = "image/*")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ){
                        Text(text = stringResource(id = R.string.pick_image))
                    }
                }
            }

            item{
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    if(viewModel.imageUri.value != null){
                        AsyncImage(
                            model = viewModel.imageUri.value,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)
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
                            //save data into firebase realtime database using : userId
                            //if success, navigate to patient homepage
                            try{
                                viewModel.onConfirmSubmit{
                                    Log.d("SaveSuccess", "Navigate to patient home")
                                    //clear cache, if the image are not stored, this would be the reason
                                    cacheDir.deleteRecursively()
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
                                !viewModel.isLoading.value,
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFF821131),
                        ),
                    ) {
                        Text(text = if(viewModel.isLoading.value) stringResource(id = R.string.saving_data) else stringResource(R.string.confirm))
                    }
                }
            }
        }
    }

}
