package com.example.safebox.features.fillprofile.domain.usecase

import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.data.repository.DataRepository

class GetUserRoleUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(userId: String): Role {
        return repository.getUserRole(userId = userId)
    }
}