package com.example.safebox.features.fillprofile.presentation.ui

import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.data.repository.FirebaseImageRepository
import com.example.safebox.features.fillprofile.data.repository.FirebaseRepository
import com.example.safebox.features.fillprofile.domain.usecase.SavePsychologistDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModel
import com.example.safebox.features.fillprofile.presentation.viewmodel.PsychologistViewModelFactory

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

    var specializationTextFieldNotBlank = emptyList<Boolean>().toMutableList()
    var experienceTextFieldNotBlank = emptyList<Boolean>().toMutableList()

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
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Psychologist Fill Profile",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(50.dp)
            )

            LazyColumn {

                items(count = 1){
                    OutlinedTextField(
                        value = psychologistData.value.name,
                        onValueChange = { viewModel.onNameChange(it) },
                        label = {
                            Text(text = "Name")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    OutlinedTextField(
                        value = psychologistData.value.workLocation,
                        onValueChange = { viewModel.onWorkLocationChange(it) },
                        label = {
                            Text(text = "Work Location")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                    )

                    OutlinedTextField(
                        value = psychologistData.value.phoneNumber,
                        onValueChange = { viewModel.onPhoneNumberChange(it) },
                        label = {
                            Text(text = "Phone Number")
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                //this is for availability
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(color = Color.Cyan)
                    ) {
                        Column {
                            Text(
                                text = "Availability",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )

                            // Replacing LazyColumn with Column if availability is a small list
                            psychologistData.value.availability.forEachIndexed { index, item ->
                                val day = viewModel.getDay(index)

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = item,
                                        onCheckedChange = {
                                            viewModel.onAvailabilityChange(index)
                                        }
                                    )

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Text(text = day.value)
                                }
                            }
                        }
                    }
                }

                //this is for specialization
                item{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(color = Color.Cyan)
                    ){
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Specialization",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Button(
                                    onClick = {
                                        //this is will add the specialization when user click
                                        //this is dynamic
                                        viewModel.onAddSpecialization()
                                        specializationTextFieldNotBlank = mutableListOf(false, false)
                                    }
                                ) {
                                    Text(text = "Add")
                                }
                            }

                            //this is the dialog of specialization
                            if(viewModel.showSpecializationDialog.value){
                                //show dialog
                                Dialog(
                                    onDismissRequest = {
                                        viewModel.onCancelSpecialization()
                                    }
                                ) {
                                    Box{
                                        Column {
                                            Text(text = "Add Specialization")

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .specializations
                                                    .last()
                                                    .field,
                                                onValueChange = {
                                                    viewModel.onFieldChange(it)
                                                    specializationTextFieldNotBlank[0] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Field")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .specializations
                                                    .last()
                                                    .description,
                                                onValueChange = {
                                                    viewModel.onDescriptionChange(it)
                                                    specializationTextFieldNotBlank[1] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Description")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceEvenly
                                            ){
                                                Button(
                                                    onClick = {
                                                        viewModel.onCancelSpecialization()
                                                        specializationTextFieldNotBlank = emptyList<Boolean>().toMutableList()
                                                    }
                                                ) {
                                                    Text(text = "Cancel")
                                                }

                                                Button(
                                                    onClick = {
                                                        viewModel.onSaveSpecialization()
                                                    },
                                                    enabled = !specializationTextFieldNotBlank.contains(false)
                                                ) {
                                                    Text(text = "Save")
                                                }
                                            }

                                        }
                                    }
                                }
                            }

                            psychologistData.value.specializations.toMutableList().forEach{ specialization ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(text = "${specialization.field} | ${specialization.description}")

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Button(
                                        onClick = {
                                            //this is to remove the selected specialization
                                            viewModel.onRemoveSpecialization(specialization = specialization)
                                        }
                                    ){
                                        Text(text = "Remove")
                                    }

                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                        }
                    }
                }

                //this is for experience
                item{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(color = Color.Cyan)
                    ){
                        Column{
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "Experience",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Button(
                                    onClick = {
                                        //this is will add the specialization when user click
                                        //this is dynamic
                                        viewModel.onAddExperience()
                                        experienceTextFieldNotBlank = List(size = 6){false}.toMutableList()
                                    }
                                ) {
                                    Text(text = "Add")
                                }
                            }

                            //show experience dialog
                            if(viewModel.showExpDialog.value){

                                Dialog(
                                    onDismissRequest = {
                                        viewModel.onCancelExperience()
                                    }
                                ) {
                                    Box{
                                        Column {
                                            Text(text = "Add Experience")

                                            //this is for the year (number type only)
                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .years
                                                    .toString(),
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "Years", data = it)
                                                    experienceTextFieldNotBlank[0] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Years")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                                //this is to make sure the input is number
                                                keyboardOptions = KeyboardOptions(
                                                    keyboardType = KeyboardType.Number
                                                )
                                            )

                                            //this is for institution
                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .institution,
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "Institution", data = it)
                                                    experienceTextFieldNotBlank[1] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Institution")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .role,
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "Role", data = it)
                                                    experienceTextFieldNotBlank[2] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Role")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .startDate,
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "StartDate", data = it)
                                                    experienceTextFieldNotBlank[3] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Start Date (DD/MM/YYYY)")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .endDate,
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "EndDate", data = it)
                                                    experienceTextFieldNotBlank[4] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "End Date (DD/MM/YYYY)")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            OutlinedTextField(
                                                value = psychologistData
                                                    .value
                                                    .experiences
                                                    .last()
                                                    .description,
                                                onValueChange = {
                                                    viewModel.onExpDataChange(attribute = "Description", data = it)
                                                    experienceTextFieldNotBlank[5] = it.isNotBlank()
                                                },
                                                label = {
                                                    Text(text = "Description")
                                                },
                                                modifier = Modifier.fillMaxWidth().padding(10.dp)
                                            )

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceEvenly
                                            ){
                                                Button(
                                                    onClick = {
                                                        viewModel.onCancelExperience()
                                                        experienceTextFieldNotBlank = emptyList<Boolean>().toMutableList()
                                                    }
                                                ) {
                                                    Text(text = "Cancel")
                                                }

                                                Button(
                                                    onClick = {
                                                        viewModel.onSaveExperience()
                                                    },
                                                    enabled = !experienceTextFieldNotBlank.contains(false)
                                                ) {
                                                    Text(text = "Save")
                                                }
                                            }

                                        }
                                    }
                                }

                            }

                            psychologistData.value.experiences.toMutableList().forEach{ experience ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        text = "${experience.institution} | ${experience.years} " +
                                                if(experience.years > 1) "years" else "year"
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Button(
                                        onClick = {
                                            viewModel.onRemoveExperience(experience = experience)
                                        }
                                    ){
                                        Text(text = "Remove")
                                    }

                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }

                        }
                    }
                }

                //this is for image picker
                item{

                    Column {

                        //this button to pick image
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
                                modifier = Modifier.padding(10.dp)
                            )

                        }

                    }

                }

                item{
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
    }
}