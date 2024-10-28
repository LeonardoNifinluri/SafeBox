package com.example.safebox.features.auth.domain.model

data class SignUpData(
    val email: String = "",
    val password: String = "",
    val role: Role = Role.UNKNOWN
)
