package com.example.examfriendlychatter.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.examfriendlychatter.data.Message
import com.example.examfriendlychatter.presentation.composables.ChatMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoom() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
            .semantics { contentDescription = "Scaffold" },
        topBar = {
            TopAppBar(
                title = { Text("ExamChatter: The exam friendly chatroom!") },
                modifier = Modifier.semantics { contentDescription = "TopAppBar" }
            )
        }
    ) { innerPadding ->
        var messageList by remember {
            mutableStateOf(
                listOf(
                    Message("Hello", "Me"),
                    Message("Hi", "Me2"),
                )
            )
        }
        var currentUser by remember { mutableStateOf(getCurrentUser(messageList.size)) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .semantics { contentDescription = "ChatScreen" }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 96.dp)
                    .semantics { contentDescription = "MessageList" },
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(messageList) { index, message ->
                    val isLeft = index % 2 == 0
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .semantics { contentDescription = "MessageRow" },
                        horizontalArrangement = if (isLeft) {
                            Arrangement.Start
                        } else {
                            Arrangement.End
                        }
                    ) {
                        ChatMessage(message = message, isLeft = isLeft)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .semantics { contentDescription = "InputField" },
                horizontalArrangement =  Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var currentMessage by remember { mutableStateOf("") }
                val focusManager = LocalFocusManager.current

                TextField(
                    value = currentMessage,
                    onValueChange = { currentMessage = it },
                    label = { Text("Enter message",
                        modifier = Modifier.semantics { contentDescription = "EnterMessage" },
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        ) },
                    modifier = Modifier
                        .height(60.dp)
                        .background(
                            MaterialTheme.colorScheme.tertiary,
                        )
                        .semantics { contentDescription = "MessageInput" },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedLabelColor = Color.Black,
                        focusedLabelColor = Color.Black
                    )
                )
                Button(
                    onClick = {
                        messageList += Message(currentMessage, currentUser)
                        currentMessage = ""
                        currentUser = getCurrentUser(messageList.size)
                        focusManager.clearFocus()
                    },
                    modifier = Modifier
                        .height(80.dp)
                        .padding(16.dp)
                        .semantics { contentDescription = "SendButton" }
                ) {
                    Text("Send", color = Color.Black)
                }
            }
        }
    }
}

fun getCurrentUser(messageListSize: Int): String {
    return if (messageListSize % 2 == 0) {
        "Me"
    } else {
        "Me2"
    }
}