package message

import data.MessageStore
import java.time.LocalDateTime

object MessageUtils {
    fun storeSystemMessage(info: String) = MessageStore.addOrUpdateMessage(
        Message(
            LocalDateTime.now(),
            "ff",
            info,
            'I',
            -1
        )
    )

    fun storeUserMessage(msg: String) = MessageStore.addOrUpdateMessage(
        Message(
            LocalDateTime.now(),
            "0f",
            msg,
            'I',
            -1
        )
    )
}