package com.example.androidxtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidxtest.model.Message
import com.example.androidxtest.repository.HelloRepo
import com.example.androidxtest.repository.MessagesRepo
import com.example.androidxtest.repository.MessagesRepoImpl

class MainActivityViewModel(private val repoHello: HelloRepo,
                            private val firebaseMessage: MessagesRepoImpl): ViewModel() {

    fun repoSayHello() = repoHello.sayHello()

    fun getRepoMessages(getMesssage: MessagesRepo) {
        // firebaseMessage.getMessa)

        firebaseMessage.getMessage(messagesRepo = object: MessagesRepo {
            override fun getMessage(messagesList: MutableList<Message>) {
                getMesssage.getMessage(messagesList)
            }
        })

    }

}