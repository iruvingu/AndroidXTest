package com.example.androidxtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.androidxtest.adapters.MessageAdapter
import com.example.androidxtest.model.Message
import com.example.androidxtest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instiate our custom adapter
        messageAdapter = MessageAdapter()

        recyclerView.adapter = messageAdapter

        // Initiate the observers on viewModel fields and then starts the API request
        initViewModel()

    }

    private fun initViewModel() {
        // Observe the cat list and update the adapter if we get a new one from firestore
        mainActivityViewModel.messageList.observe(this, Observer { newMessagesList ->
            messageAdapter.updateMessages(newMessagesList)
        } )
        // Observes the showLoading value and display or hide our progress bar
        mainActivityViewModel.showLoading.observe(this, Observer { showLoading ->
            progressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        // Observes showError value and display the error message as a toast
        mainActivityViewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })

        // The observers are set, we can now ask API to load a data list
        mainActivityViewModel.loadMessages()

    }
}
