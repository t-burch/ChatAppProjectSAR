package data

import message.Message
import java.time.LocalDateTime

object MessageStore {
    @Volatile
    private var store: MutableMap<LocalDateTime, Message> = HashMap()

    fun addOrUpdateMessage(msg: Message) {
        store[LocalDateTime.now()] = msg
    }

    fun getAllMessages(): Map<LocalDateTime, Message> = store.toMap()
}

