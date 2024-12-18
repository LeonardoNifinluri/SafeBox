package com.example.safebox.features.fillprofile.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safebox.features.fillprofile.domain.model.psychologist.Experience
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.fillprofile.domain.model.psychologist.Specialization
import com.example.safebox.features.fillprofile.domain.usecase.SavePsychologistDataUseCase
import com.example.safebox.features.fillprofile.domain.usecase.UploadImageUseCase
import kotlinx.coroutines.launch

class PsychologistViewModel(
    private val userId: String,
    private val email: String,
    private val uploadImageUseCase: UploadImageUseCase,
    private val savePsychologistDataUseCase: SavePsychologistDataUseCase
): ViewModel() {

    private val _psychologistData = mutableStateOf(Psychologist())
    val psychologistData: State<Psychologist> = _psychologistData

    private val _imageUri = mutableStateOf<Uri?>(value = null)
    val imageUri: State<Uri?> = _imageUri

    //this is to show the specialization dialog
    //why use this pattern? : to separate mutable and immutable why? to make sure only view model can have the control of the variable
    private val _showSpecializationDialog = mutableStateOf(value = false)
    val showSpecializationDialog: State<Boolean> = _showSpecializationDialog  // Expose as boolean

    //this is to show experience dialog
    private val _showExpDialog = mutableStateOf(value = false)
    val showExpDialog: State<Boolean> = _showExpDialog

    //this is the image url in firebase storage
    private val _imageUrl = mutableStateOf(value = "")
//    val imageUrl: State<String> = _imageUrl

    //this is success result when save psychologist data
    private val _saveSuccess = mutableStateOf(value = false)
//    val saveSuccess: State<Boolean> = _saveSuccess

    private val _isLoading = mutableStateOf(value = false)
    val isLoading: State<Boolean> = _isLoading

    private val _showAvailabilityModal = mutableStateOf(value = false)
    val showAvailabilityModal: State<Boolean> = _showAvailabilityModal

    private val _showSpecializationModal = mutableStateOf(value = false)
    val showSpecializationModal: State<Boolean> = _showSpecializationModal

    private val _showExperienceModal = mutableStateOf(value = false)
    val showExperienceModal: State<Boolean> = _showExperienceModal

    //this is to handle name when changing
    fun onNameChange(newName: String){
        _psychologistData.value = _psychologistData.value.copy(name = newName)
    }

    fun onWorkLocationChange(newLocation: String){
        _psychologistData.value = _psychologistData.value.copy(workLocation = newLocation)
    }

    fun onPhoneNumberChange(newPhoneNumber: String){
        _psychologistData.value = _psychologistData.value.copy(phoneNumber = newPhoneNumber)
    }

    fun showModal(field: Int){
        when(field){
            0 -> _showAvailabilityModal.value = true
            1 -> _showSpecializationModal.value = true
            2 -> _showExperienceModal.value = true
        }
    }
    fun dismissModal(field: Int){
        when(field){
            0 -> _showAvailabilityModal.value = false
            1 -> _showSpecializationModal.value = false
            2 -> _showExperienceModal.value = false
        }
    }
    fun onAvailabilityChange(idx: Int){
        // to update the list, need to create a new list since i'm using mutableStateOf
        val newAvailabilityList = _psychologistData.value.availability.mapIndexed{
            index, value ->
            if(index == idx) !value else value
        }
        _psychologistData.value = _psychologistData.value.copy(availability = newAvailabilityList)
    }

    fun onImageChange(newImageUri: Uri){
        _imageUri.value = newImageUri
    }

    //this is for specialization
    fun onAddSpecialization(){
        _showSpecializationDialog.value = true
        _psychologistData.value.specializations.add(Specialization())
        //I don't have to do this since i'm working with SnapshotStateList
        //_psychologistData.value = _psychologistData.value.copy(specializations = currentSpecialization)
    }

    fun onCancelSpecialization(){
        //this is to cancel add specialization
        //this is will remove the last element
        val specializations = psychologistData.value.specializations
        if(specializations.isNotEmpty()){
            _psychologistData.value.specializations.removeAt(specializations.lastIndex)
        }else{
            Log.d("Specializations", "SpecializationList is empty")
        }
        Log.d(
            "OnCancelSpecialization",
            "Canceled"
        )
        _showSpecializationDialog.value = false
        //_psychologistData.value = _psychologistData.value.copy(specializations = specializations)
    }

    fun onSaveSpecialization(){
        //to know if the last element really holds the data
        Log.d(
            "OnSaveSpecialization",
            "Last Specialization data : " +
                    "${psychologistData
                        .value
                        .specializations[
                        psychologistData
                            .value
                            .specializations
                            .lastIndex
                        ]
                    } and size: ${psychologistData.value.specializations.size}"
        )
        //this is will create a data class of specialization and will be appended to specialization list

        //update the visibility of dialog
        _showSpecializationDialog.value = false
    }

    fun onRemoveSpecialization(){
        //remove last element
        if(psychologistData.value.specializations.isNotEmpty()){
            _psychologistData.value.specializations.removeAt(
                psychologistData.value.specializations.lastIndex
            )
        }
    }

    fun onFieldChange(newField: String){
        if(psychologistData.value.specializations.isNotEmpty()){
            //get the last specialization in specializations
            val lastIndex = psychologistData.value.specializations.lastIndex
            var lastSpecialization = psychologistData.value.specializations[lastIndex]

            //replace the value of the previous lastSpec with new lastSpec
            lastSpecialization = lastSpecialization.copy(field = newField)

            //update the last specialization
            _psychologistData.value.specializations[lastIndex] = lastSpecialization
        }else{
            Log.d("Specialization-ChangeField", "Specialization is empty")
        }
    }

    fun onDescriptionChange(newDesc: String){
        if(psychologistData.value.specializations.isNotEmpty()){
            //get the last specialization in specializations
            val lastIndex = psychologistData.value.specializations.lastIndex
            var lastSpecialization = psychologistData.value.specializations[lastIndex]

            //replace the value of the previous lastSpec with new lastSpec
            lastSpecialization = lastSpecialization.copy(description = newDesc)

            //update the last specialization
            _psychologistData.value.specializations[lastIndex] = lastSpecialization
        }else{
            Log.d("Specialization-ChangeField", "Specialization is empty")
        }
    }

    //this is for experience
    fun onAddExperience(){
        _showExpDialog.value = true
        val experiences = _psychologistData.value.experiences
        experiences.add(Experience())
    }

    fun onCancelExperience(){
        val experiences = _psychologistData.value.experiences
        if(experiences.isNotEmpty()){
            experiences.removeAt(experiences.lastIndex)
        }else{
            Log.d("ExperiencesOnCancel", "Experiences is empty")
        }
        Log.d(
            "OnCancelExp",
            "Canceled"
        )
        _showExpDialog.value = false
    }

    fun onSaveExperience(){
        Log.d(
            "OnSaveExperience",
            "Last Experience data : " +
                    "${_psychologistData
                        .value
                        .experiences[
                        _psychologistData
                            .value
                            .experiences
                            .lastIndex
                        ]
                    }"
        )

        _showExpDialog.value = false
    }

    fun onRemoveExperience(){
        //remove last element :
        if(psychologistData.value.experiences.isNotEmpty()){
            _psychologistData.value.experiences.removeAt(
                psychologistData.value.experiences.lastIndex
            )
        }
    }

    fun onExpDataChange(attribute: String, data: String){
        val currentExperiences = _psychologistData.value.experiences
        val lastIndex = currentExperiences.lastIndex
        var lastExperiences = currentExperiences.last()

        when(attribute){
            "Years" -> {
                val year = if(data.isBlank()) 0 else data.toInt()
                lastExperiences = lastExperiences.copy(years = year)
            }
            "Institution" -> lastExperiences = lastExperiences.copy(institution = data)
            "Role" -> lastExperiences = lastExperiences.copy(role = data)
            "StartDate" -> lastExperiences = lastExperiences.copy(startDate = data)
            "EndDate" -> lastExperiences = lastExperiences.copy(endDate = data)
            "Description" -> lastExperiences = lastExperiences.copy(description = data)
        }

        currentExperiences[lastIndex] = lastExperiences
    }

    fun onConfirmSubmit(onSaveSuccess: () -> Unit){
        _isLoading.value = true
        viewModelScope.launch{
            try{
                val url = uploadImageUseCase(imageUri.value!!)
                _imageUrl.value = url
                Log.d("UploadImage-confirmSubmit", "$url success")
            }catch(e: Exception){
                Log.d("UploadImage-confirmSubmit", "Error when upload image")
            }finally {
                _psychologistData.value = _psychologistData.value.copy(profileImage = _imageUrl.value)
                _psychologistData.value = _psychologistData.value.copy(email = email)
                try{
                    _saveSuccess.value = savePsychologistDataUseCase(
                        userId = userId,
                        psychologistData = _psychologistData.value
                    )

                    Log.d("SavingData-confirmSubmit", "Success")

                    Log.d("PsychologistData", _psychologistData.value.toString())

                    //apply the onSaveSuccess
                    if(_saveSuccess.value){
                        onSaveSuccess()
                    }
                }catch (e: Exception){
                    Log.d("SaveStatus-confirmSubmit", "error : ${e.message}")
                }finally {
                    //reset all the states
                    _isLoading.value = false
                    _psychologistData.value = Psychologist()
                    _imageUri.value = null
                    _imageUrl.value = ""
                }
            }
        }
    }

}