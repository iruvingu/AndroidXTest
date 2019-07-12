package com.example.androidxtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidxtest.repository.HelloRepo
import com.example.androidxtest.repository.MessagesRepo

class MainActivityViewModel(private val repoHello: HelloRepo,
                            val firebaseMessage: MessagesRepo): ViewModel() {

    fun repoSayHello() = repoHello.sayHello()

    fun getRepoMessages() {
        firebaseMessage.getMessage()
    }

}