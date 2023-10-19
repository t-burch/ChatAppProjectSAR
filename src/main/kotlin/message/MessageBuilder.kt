package message

import java.time.LocalDate
import java.time.LocalDateTime

object MessageBuilder {
    private val message = Message(
        LocalDateTime.now(),
        "",
        "",
        'C',
        1
    )

    fun build() = message

    fun timestamp(new: LocalDateTime) = message.apply{
        timestamp = new
        return this
    }
    fun token(new: String) = message.apply{
        token = new
        return this
    }
    fun content(new: String) = message.apply{
        content = new
        return this
    }
    fun protocol(new: Char) = message.apply{
        protocol = new
        return this
    }
    fun version(new: Short) = message.apply{
        version = new
        return this
    }
}