package com.example.safebox.features.fillprofile.domain.model.patient

import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.patientactivity.diary.data.datasource.Diary

data class Patient(
    var name: String = "",
    var email: String = "",
    var birthdate: String = "", //use calendar
    var gender: Gender = Gender.NOT_SET, //enum gender
    val role: Role = Role.PATIENT, //enum role
    var address: String = "",
    var phoneNumber: String = "",
    var profileImageURL: String = "", //this is a url to firebase storage
    val medicalRecords: List<MedicalRecord>? = null,
    val diaries: List<Diary>? = null
)
