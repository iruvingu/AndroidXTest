package com.example.androidxtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidxtest.model.Message
import com.example.androidxtest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messagesList = mutableListOf<Message>()

        btnHello.setOnClickListener {
            Toast.makeText(this, mainActivityViewModel.repoSayHello(), Toast.LENGTH_LONG).show()

            //mainActivityViewModel.getRepoMessages(messagesList)

            messagesList.forEach { message ->
                Log.d("Mensaje", message.texto)
            }

        }

    }
}
