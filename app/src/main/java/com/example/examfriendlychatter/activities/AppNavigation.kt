package com.example.examfriendlychatter.activities

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examfriendlychatter.presentation.screens.ChatRoom
import com.example.examfriendlychatter.presentation.screens.HomeScreen
import com.example.examfriendlychatter.viewmodels.ChatViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val chatViewModel: ChatViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "homescreen"
    ) {

        composable(route = "chatroom") {
            ChatRoom(
                navController = navController,
                chatViewModel = chatViewModel
            )
        }

        composable(route = "homescreen") {
            HomeScreen(
                navController = navController
            )
        }
    }
}