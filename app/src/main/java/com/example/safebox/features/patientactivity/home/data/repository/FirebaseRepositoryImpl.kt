package com.example.safebox.features.patientactivity.home.data.repository

import android.util.Log
import com.example.safebox.features.auth.domain.model.Role
import com.example.safebox.features.fillprofile.domain.model.patient.Patient
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.home.domain.repository.FirebaseRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl: FirebaseRepository {
    private val database = FirebaseDatabase.getInstance().reference
    override suspend fun getPsychologistData(): List<Psychologist> {
        try {
            val snapshot = database.child("user")
                .orderByChild("role")
                .equalTo(Role.PSYCHOLOGIST.name)
                .get().await()

            if (snapshot.exists()) {
                //this is error because firebase return specialization and experience as ArrayList not as SnapshotStateList
                val psychologists = snapshot.children.mapNotNull { dataSnapshot ->
                    dataSnapshot.getValue(Psychologist::class.java)
                }
                return psychologists
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e("ErrorFetching", "Error when fetching psy data: ", e)
            Log.e("ErrorFetching", "Error when fetching psy data: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun getPatientData(userId: String): Patient? {
        try{
            val snapshot = database.child("user")
                .child(userId)
                .get().await()
            if(snapshot.exists()){
                val patient = snapshot.getValue(Patient::class.java)
                return patient
            }else{
                return null
            }
        }catch (e: Exception){
            Log.e("FetchError", "Error when fetching patient data", e)
            return null
        }
    }

}