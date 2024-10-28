package com.example.safebox.features.fillprofile.data.repository

import android.util.Log
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRepository: DataRepository {

    private val url = "https://safebox-3bada-default-rtdb.asia-southeast1.firebasedatabase.app/"
    private val database = FirebaseDatabase.getInstance(url).reference

    override suspend fun savePatientData(
        userId: String,
        patientData: Patient
    ): Boolean {
        return try{
            database.child("user")
                .child("patient")
                .child(userId)
                .setValue(patientData)
                .await()
            Log.d("SaveDataStatus", "Success")
            true
        }catch(e: Exception){
            Log.d("SaveDataStatus", "Fail")
            false
        }
    }

    override suspend fun savePsychologistData(
        userId: String,
        psychologistData: Psychologist
    ): Boolean {
        return try{
            database.child("user")
                .child("psychologist")
                .child(userId)
                .setValue(psychologistData)
                .await()
            Log.d("SavePsychologistStatus", "Success")
            true
        }catch (e: Exception){
            Log.d("SavaPsychologistStatus", "Fail")
            false
        }
    }


}