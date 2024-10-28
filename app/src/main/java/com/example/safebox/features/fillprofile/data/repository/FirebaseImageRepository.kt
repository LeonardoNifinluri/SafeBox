package com.example.safebox.features.fillprofile.data.repository

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseImageRepository: ImageRepository {

    //instance of FirebaseStorage
    private val storageRef = FirebaseStorage.getInstance().reference

    override suspend fun uploadImage(imageUri: Uri): String {
        //make sure the firebase storage rules properly set
        return try{
            val fileName = UUID.randomUUID().toString() //to generate random name for file
            val imageRef = storageRef.child("profileImages/$fileName.jpg")

            //upload file to firebase storage and get the url
            val uploadTask = imageRef.putFile(imageUri)
            uploadTask.await()
            val downloadedURL = imageRef.downloadUrl.await()

            //return the downloadedURL as string
            downloadedURL.toString()
        }catch (e: Exception){
            throw e
        }
    }
}