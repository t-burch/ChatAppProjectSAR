package message

import java.time.LocalTime

data class Message(
    private val timestamp: LocalTime,
    private val token: String,
    private val content: String,
    private val protocol: Char,
    private val version: Short
)
