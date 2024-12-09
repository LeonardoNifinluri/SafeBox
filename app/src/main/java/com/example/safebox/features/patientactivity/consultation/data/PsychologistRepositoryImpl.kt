package com.example.safebox.features.patientactivity.consultation.data

import android.util.Log
import com.example.safebox.features.fillprofile.domain.model.psychologist.Psychologist
import com.example.safebox.features.patientactivity.consultation.domain.repository.PsychologistRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class PsychologistRepositoryImpl: PsychologistRepository {
    private val database = FirebaseDatabase.getInstance().reference
    override suspend fun getPsychologistById(userId: String): Psychologist? {
        try{
            val snapshot = database.child("user")
                .child(userId)
                .get().await()
            if(snapshot.exists()){
                val psychologist = snapshot.getValue(Psychologist::class.java)
                Log.d("GetPsyByIdStatus", "Success")
                return psychologist
            }else{
                Log.d("GetPsyByIdStatus", "#$userId doesn't exist")
                return null
            }
        }catch (e: Exception){
            Log.e("GetPsyByIdStatus", "Error when fetch data #$userId: ${e.message}")
            return null
        }
    }
}