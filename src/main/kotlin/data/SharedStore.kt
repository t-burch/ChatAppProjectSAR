package data

import message.Message

object DataStore {
    @Volatile
    var composingBuffer: String = ""
    @Volatile
    var cursorPosition: Int = 0
    @Volatile
    var messages: MutableList<Message> = mutableListOf()
}