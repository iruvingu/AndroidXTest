package com.example.androidxtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidxtest.SingleLiveEvent
import com.example.androidxtest.UseCaseResult
import com.example.androidxtest.model.Message
import com.example.androidxtest.repository.HelloRepo
import com.example.androidxtest.repository.MessageRepoByCoroutines
import com.example.androidxtest.repository.MessagesRepo
import com.example.androidxtest.repository.MessagesRepoImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(private val repoHello: HelloRepo,
                            private val firebaseMessage: MessagesRepoImpl,
                            private val messageRepoByCoroutines: MessageRepoByCoroutines): ViewModel(), CoroutineScope {

    // Coroutine's background job
    private val jobMessage = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + jobMessage

    private val showLoading: MutableLiveData<Boolean>? = null

    val messageList: MutableLiveData<List<Message>>? = null

    val showError = SingleLiveEvent<String>()

    fun loadMessages() {
        // Show progressBar during the operation on the MAIN (default) thread
        showLoading?.value = true

        // launch the Coroutine
        launch {
            // Switching from MAIN to IO thread for API operation
            // Update our data list with the new one from firebase
            val result = withContext(Dispatchers.IO) { messageRepoByCoroutines.getMessagesList() }
            // Hide progressBar once the operation is done on the MAIN (default) thread
            showLoading?.value = false
            when (result) {
                is UseCaseResult.Success -> messageList?.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception.message
            }
        }

    }

    fun repoSayHello() = repoHello.sayHello()

    fun getRepoMessages(getMesssage: MessagesRepo) {
        // firebaseMessage.getMessa)

        firebaseMessage.getMessage(messagesRepo = object: MessagesRepo {
            override fun getMessage(messagesList: MutableList<Message>) {
                getMesssage.getMessage(messagesList)
            }
        })

    }

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        jobMessage.cancel()
    }

}