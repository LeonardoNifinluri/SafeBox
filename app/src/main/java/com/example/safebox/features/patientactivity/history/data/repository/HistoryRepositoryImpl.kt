package com.example.safebox.features.patientactivity.history.data.repository

import android.util.Log
import com.example.safebox.features.patientactivity.history.domain.model.History
import com.example.safebox.features.patientactivity.history.domain.repository.HistoryRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.util.UUID

class HistoryRepositoryImpl: HistoryRepository {
    private val database = FirebaseDatabase.getInstance().reference
    override suspend fun createHistory(
        userId: String,
        history: History
    ): Boolean {
        val uuid = UUID.randomUUID().toString()
        return try{
            database.child("history")
                .child(userId)
                .child(uuid)
                .setValue(history)
                .await()
            Log.d("SaveHistoryStatus", "Success")
            true
        }catch (e: Exception){
            Log.e("SaveHistoryStatus", "Fail with error: ${e.message}")
            false
        }
    }

    override suspend fun getHistories(userId: String): List<History> {
        try {
            val snapshot = database.child("history")
                .child(userId)
                .get().await()
            if(snapshot.exists()){
                val histories = snapshot.children.mapNotNull { dataSnapshot ->
                    val history = dataSnapshot.getValue(History::class.java)
                    history?.copy(id = dataSnapshot.key ?: "")
                }
                Log.d("GetHistoriesStatus", "Success and Not Empty")
                Log.d("Histories", histories.toString())
                return histories
            }else{
                Log.d("GetHistoriesStatus", "Success but empty")
                return emptyList()
            }
        }catch (e: Exception){
            Log.e("GetHistoriesStatus", "Fail with error: ${e.message}")
            return emptyList()
        }
    }
}