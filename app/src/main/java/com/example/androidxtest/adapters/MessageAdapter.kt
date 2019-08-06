package com.example.androidxtest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidxtest.R
import com.example.androidxtest.model.Message
import kotlinx.android.synthetic.main.item_message.view.*
import kotlin.properties.Delegates

class MessageAdapter: RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder>() {

    // Our data list is going to be notified when we assign a new list of data to it
    private var messageList: List<Message> by Delegates.observable(emptyList()) { _, _, _->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_message, parent, false)
        return MessageAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MessageAdapterViewHolder, position: Int) {
        // verify if position exists already
        if (position != RecyclerView.NO_POSITION) {
            val message: Message = messageList[position]
            holder.bindView(message)
        }
    }

    // update recyclerview's data
    fun updateMessages(newMessagesList: List<Message>) {
        messageList = newMessagesList
    }

    // inner class
    class MessageAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(message: Message) {

            itemView.apply {
                textView.text = message.texto
            }

            Log.d("MSJ","message: $message.texto" )

        }

    }

}