package com.example.examfriendlychatter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.examfriendlychatter.data.Message

class ChatViewModel : ViewModel() {
    private val _messageList = mutableStateListOf<Message>()
    val messageList: List<Message> get() = _messageList

    init {
        _messageList.addAll(
            listOf(
                Message("Hello", "Me"),
                Message("Hi", "Me2")
            )
        )
    }
    fun addMessage(message: String, from: String) {
        _messageList.add(Message(message,from))
    }

    fun getCurrentUser(): String {
        return if (_messageList.size % 2 == 0) {
            "Me"
        } else {
            "Me2"
        }
    }
}