package com.example.androidxtest.repository

import android.util.Log
import com.example.androidxtest.UseCaseResult
import com.example.androidxtest.model.Contact
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface ContactsRepo {
    suspend fun getContactsList(): UseCaseResult<List<Contact>>
}

class ContactsRepoImpl: ContactsRepo {

    private val firestoreDB = FirebaseFirestore.getInstance()

    override suspend fun getContactsList(): UseCaseResult<List<Contact>> {

        return try {

            val snapshot = firestoreDB
                .collection("contacts")
                .get()
                .await()

            val contactsList: List<Contact> = snapshot.toObjects(Contact::class.java)

            contactsList.forEach { contact -> Log.d("contact", "name: $contact.name ; phone: $contact.phone") }

            UseCaseResult.Success(contactsList)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }

    }

}