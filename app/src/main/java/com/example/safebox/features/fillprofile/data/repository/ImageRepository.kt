package com.example.safebox.features.fillprofile.data.repository

import android.net.Uri

interface ImageRepository {
    suspend fun uploadImage(imageUri: Uri): String //this is will return url of the image that is stored in firebase storage
}