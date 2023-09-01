package message

import java.time.LocalDateTime

data class Message(
    var timestamp: LocalDateTime,
    var token: String,
    var content: String,
    var protocol: Char,
    var version: Short
)
