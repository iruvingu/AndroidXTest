package com.example.androidxtest.firestore

import com.example.androidxtest.model.Message
import kotlinx.coroutines.Deferred

interface MessageFirestore {

    fun getMessageFirestore(): Deferred<List<Message>>

}