package com.example.safebox.features.fillprofile.domain.usecase

import android.net.Uri
import com.example.safebox.features.fillprofile.data.repository.ImageRepository

class UploadImageUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(imageUri: Uri): String{
        //make sure the rules in firebase storage is proper
        return repository.uploadImage(imageUri = imageUri)
    }
}