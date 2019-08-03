package com.example.androidxtest.repository

import android.util.Log
import com.example.androidxtest.model.Message
import com.google.firebase.firestore.FirebaseFirestore

interface MessagesRepo {
    fun getMessage(messagesList: MutableList<Message>)
}

class MessagesRepoImpl {

    private val firestoreDB = FirebaseFirestore.getInstance()

     fun getMessage(messagesRepo: MessagesRepo) {
        val listOfMessages: MutableList<Message> = mutableListOf()
        try {

            firestoreDB.collection("mensajes").get().addOnSuccessListener { result ->
                result.forEach {
                    Log.d("MSG", "${it.id} => ${it.data}")
                    listOfMessages.add(it.toObject(Message::class.java))
                }
                messagesRepo.getMessage(listOfMessages)
            }.addOnFailureListener { exception ->
                Log.w("Error", "Error getting documents.", exception as Throwable?)
                listOfMessages.clear()
            }
        } catch (e: Exception){
            Log.w("Error", "Exception: ", e)
            listOfMessages.clear()
        }

    }

}