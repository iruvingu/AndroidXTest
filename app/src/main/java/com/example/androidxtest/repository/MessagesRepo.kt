package com.example.androidxtest.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

interface MessagesRepo {
    fun getMessage()
}

class MessagesRepoImpl(): MessagesRepo {

    private val firestoreDB = FirebaseFirestore.getInstance()

    override fun getMessage() {
        firestoreDB.collection("mensajes").get().addOnSuccessListener { result ->
            result.forEach {
                Log.d("MSG", "${it.id} => ${it.data}")
            }
        }.addOnFailureListener { exception ->
            Log.w("Error", "Error getting documents.", exception)
        }
    }

}