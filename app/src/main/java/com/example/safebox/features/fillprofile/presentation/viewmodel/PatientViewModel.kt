package com.example.safebox.features.fillprofile.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.fillprofile.domain.model.patient.Gender
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.usecase.SavePatientDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PatientViewModel(
    private val userId: String,
    private val email: String,
    private val uploadImageUseCase: UploadImageUseCase,
    private val savePatientDataUseCase: SavePatientDataUseCase
): ViewModel() {

    private val _patientData = mutableStateOf(value = Patient())
    val patientData: State<Patient> = _patientData

    //this is to get the Url
    private val _imageUrl = MutableLiveData<String>()
    private val imageUrl: LiveData<String> get() = _imageUrl

    private val _imageUri = mutableStateOf<Uri?>(value = null)
    val imageUri: State<Uri?> = _imageUri

    //this is to get result when saving data
    private val _saveSuccess = MutableLiveData<Boolean>()

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val _showDateDialog = mutableStateOf(value = false)
    val showDateDialog: State<Boolean> = _showDateDialog

    fun onNameChange(newName: String){
        _patientData.value = _patientData.value.copy(name = newName)
    }

    fun onBirthdateChange(newBirthdate: String){
        _patientData.value = _patientData.value.copy(birthdate = newBirthdate)
        _showDateDialog.value = false
    }

    fun onGenderChange(newGender: Gender){
        _patientData.value = _patientData.value.copy(gender = newGender)
    }

    fun onAddressChange(newAddress: String){
        _patientData.value = _patientData.value.copy(address = newAddress)
    }

    fun onPhoneChange(newPhone: String){
        _patientData.value = _patientData.value.copy(phoneNumber = newPhone)
    }

    //this is will store image everytime user pick image (security concern)
    //this newImage is an url from firebase storage
    fun onProfileImageChange(newImageUri: Uri){
        _imageUri.value = newImageUri
    }

    //need a function to handle the medical record of user(later)
    //default value of patientData.medicalRecords = null and patientData.diary = null

    fun onPickBirthdate(){
        _showDateDialog.value = true
    }

    fun onCancelPickDate(){
        _showDateDialog.value = false
    }

    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    fun onConfirmSubmit(onSaveSuccess: () -> Unit){
        //must get user confirmation first
        //after get userConfirmation, save the data to firebase realtime database
        _isLoading.value = true
        viewModelScope.launch {
            try{
                val url = uploadImageUseCase(imageUri.value!!)
                _imageUrl.value = url
                Log.d("Upload Image", "Success")
            }catch (e: Exception){
                Log.d("Upload Image", "Error when upload image")
            }finally {
                _patientData.value = _patientData.value.copy(profileImageURL = imageUrl.value!!)
                _patientData.value = _patientData.value.copy(email = email)
                try{
                    _saveSuccess.value = savePatientDataUseCase(
                        userId = userId,
                        patientData = patientData.value
                    )
                    Log.d("SavingDataStatus", "Success")

                    Log.d(
                        "PatientData",
                        patientData.value.phoneNumber + patientData.value.name
                    )

                    //navigate to patient home
                    if(_saveSuccess.value!!){
                        onSaveSuccess()
                    }

                }catch (e: Exception){
                    Log.d("Save Status", "Error : ${e.message}")
                }finally {
                    //reset all the states
                    _isLoading.value = false
                    _patientData.value = Patient()
                    _imageUrl.value = ""
                    _imageUri.value = null
                }
            }
        }
    }
}