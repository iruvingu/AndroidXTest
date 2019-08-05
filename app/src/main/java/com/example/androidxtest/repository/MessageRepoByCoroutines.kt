package com.example.androidxtest.repository

import android.util.Log
import com.example.androidxtest.UseCaseResult
import com.example.androidxtest.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface MessageRepoByCoroutines {

    suspend fun getMessagesList(): UseCaseResult<List<Message>>

}

class MessageRepoByCoroutinesImpl: MessageRepoByCoroutines {

    private val firestoreDB = FirebaseFirestore.getInstance()

    override suspend fun getMessagesList(): UseCaseResult<List<Message>> {

        return try {
            val listOfMessages: List<Message>

            val snapshot = firestoreDB.collection("mensajes").get().await()

            listOfMessages = snapshot.toObjects(Message::class.java)

            listOfMessages.forEach { message -> Log.d("MSG", "${message.texto} => ${message.valido.toString()}") }

            UseCaseResult.Success(listOfMessages)

        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }

    }


}