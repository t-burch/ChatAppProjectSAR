package message

import java.time.LocalDate
import java.time.LocalDateTime

object MessageBuilder {
    private val message = Message(
        LocalDateTime.now(),
        "00000000000000000000000000000000",
        "",
        'C',
        0
    )

    fun build() = message

    fun timestamp(new: LocalDateTime) = message.apply{
        timestamp = new
    }
    fun token(new: String) = message.apply{
        token = new
    }
    fun content(new: String) = message.apply{
        content = new
    }
    fun protocol(new: Char) = message.apply{
        protocol = new
    }
    fun version(new: Short) = message.apply{
        version = new
    }
}