package frontend

import java.io.Console

class InputHandler {
    private val console: Console = System.console()
    private val composing = StringBuilder()
    private var cursor = 0

    fun handle(): Pair<String, Int> {
        when (val key = console.reader().read()) {
            27 -> {
                console.reader().read()
                when (console.reader().read()) {
                    67 -> if (cursor < composing.length) cursor++  // Right arrow
                    68 -> if (cursor > 0) cursor--  // Left arrow
                }
            }
            8, 127 -> {  // Backspace/Delete key
                if (cursor > 0) {
                    composing.deleteCharAt(cursor - 1)
                    cursor--
                }
            }
            10, 13 -> {  // Newline/Carriage return
                // Here we do nothing if you don't want to handle Enter.
                // If you want to, perhaps clear the composing string or send a message.
            }
            else -> {  // Regular key press
                composing.insert(cursor, key.toChar())
                cursor++
            }
        }
        return Pair(composing.toString(), cursor)
    }
}