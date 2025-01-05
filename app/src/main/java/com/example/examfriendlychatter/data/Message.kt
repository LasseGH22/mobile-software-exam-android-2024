package com.example.examfriendlychatter.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Message (
    var message: String = "",
    var from: String = "",
    var timestamp: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
)