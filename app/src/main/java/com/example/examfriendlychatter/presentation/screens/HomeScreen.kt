package com.example.examfriendlychatter.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Button(
            content = { Text(text = "Chat Room") },
            onClick = { navController.navigate("chatroom") },
        )
    }
}