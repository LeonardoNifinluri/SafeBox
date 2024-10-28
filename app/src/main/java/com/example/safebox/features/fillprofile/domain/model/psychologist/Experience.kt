package com.example.safebox.features.fillprofile.domain.model.psychologist

data class Experience(
    var years: Int = 0,
    var institution: String = "",
    var role: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var description: String = ""
)
