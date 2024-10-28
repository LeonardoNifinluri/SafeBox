package com.example.safebox.features.fillprofile.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.fillprofile.domain.model.patient.Gender
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.usecase.SavePatientDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import kotlinx.coroutines.launch

class PatientViewModel(
    private val userId: String,
    private val email: String,
    private val uploadImageUseCase: UploadImageUseCase,
    private val savePatientDataUseCase: SavePatientDataUseCase
): ViewModel() {

    var patientData by mutableStateOf(Patient())
        private set

    //this is to get the Url
    private val _imageUrl = MutableLiveData<String>()
    private val imageUrl: LiveData<String> get() = _imageUrl
    var imageUri by mutableStateOf<Uri?>(value = null)

    //this is to get result when saving data
    private val _saveSuccess = MutableLiveData<Boolean>()

    fun onNameChange(newName: String){
        patientData = patientData.copy(name = newName)
    }

    fun onBirthdateChange(newBirthdate: String){
        patientData = patientData.copy(birthdate = newBirthdate)
    }

    fun onGenderChange(newGender: Gender){
        patientData = patientData.copy(gender = newGender)
    }

    fun onAddressChange(newAddress: String){
        patientData = patientData.copy(address = newAddress)
    }

    fun onPhoneChange(newPhone: String){
        patientData = patientData.copy(phoneNumber = newPhone)
    }

    //this is will store image everytime user pick image (security concern)
    //this newImage is an url from firebase storage
    fun onProfileImageChange(newImageUri: Uri){
        imageUri = newImageUri
    }

    //need a function to handle the medical record of user(later)
    //default value of patientData.medicalRecords = null and patientData.diary = null

    fun onConfirmSubmit(onSaveSuccess: () -> Unit){
        //must get user confirmation first
        //after get userConfirmation, save the data to firebase realtime database
        viewModelScope.launch {
            try{
                val url = uploadImageUseCase(imageUri!!)
                _imageUrl.value = url
                Log.d("Upload Image", "Success")
            }catch (e: Exception){
                Log.d("Upload Image", "Error when upload image")
            }finally {
                patientData = patientData.copy(profileImageURL = imageUrl.value!!)
                patientData = patientData.copy(email = email)
                try{
                    _saveSuccess.value = savePatientDataUseCase(
                        userId = userId,
                        patientData = patientData
                    )
                    Log.d("SavingDataStatus", "Success")

                    Log.d(
                        "PatientData",
                        patientData.phoneNumber + patientData.name
                    )

                    //navigate to patient home
                    if(_saveSuccess.value!!){
                        onSaveSuccess()
                    }

                }catch (e: Exception){
                    Log.d("Save Status", "Error : ${e.message}")
                }
            }
        }
    }
}