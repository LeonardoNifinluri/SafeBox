package com.example.safebox.features.patientactivity.diary.data

import android.util.Log
import com.example.safebox.features.patientactivity.diary.domain.model.Diary
import com.example.safebox.features.patientactivity.diary.domain.repository.DiaryRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import java.util.UUID

class DiaryRepositoryImpl: DiaryRepository {
    private val database = FirebaseDatabase.getInstance().reference
    override suspend fun createDiary(userId: String, diary:Diary): Boolean {
        //this is we save something to realtime database
        val uuid = UUID.randomUUID().toString()
        return try{
            database.child("diary")
                .child(userId)
                .child(uuid)
                .setValue(diary)
                .await()
            Log.d("SaveDiaryStatus", "Success")
            true
        }catch(e: Exception){
            Log.e("SaveDiaryStatus", "Error when save diary: ${e.message}")
            false
        }
    }

    override suspend fun getAllDiary(): Boolean {
        TODO("Not yet implemented")
    }
}