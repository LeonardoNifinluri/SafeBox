package com.example.safebox.features.fillprofile.domain.model.psychologist

import androidx.compose.runtime.mutableStateListOf
import com.example.safebox.features.auth.domain.model.Role

data class Psychologist(
    var name: String = "",
    val role: Role = Role.PSYCHOLOGIST, //enum role
    var email: String = "",
    var phoneNumber: String = "",
    var workLocation: String = "",
    var availability: List<Boolean> =  List(size = 7) { false }, //this list has length 7, idx 0 -> monday, idx 1 -> tuesday, etc. If true then available else not
    var profileImage: String = "", //this is the url of image in firebase storage
    var specializations: MutableList<Specialization> = mutableStateListOf(),
    var experiences: MutableList<Experience> = mutableStateListOf()
)
