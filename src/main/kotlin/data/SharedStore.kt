package data

import message.Message

object SharedStore {
    @Volatile
    var composingBuffer: String = ""
    @Volatile
    var cursorPosition: Int = 0
    @Volatile
    var scrollPosition: Int = 0
    @Volatile
    var messages: MutableList<Message> = mutableListOf()
    @Volatile
    var alive: Boolean = true
    @Volatile
    var identityToken: String? = null
}