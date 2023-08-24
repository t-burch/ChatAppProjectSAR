package frontend

import java.io.Console

class InputHandler {
    private val console: Console = System.console()
    private val composing = StringBuilder()
    private var cursor = 0


    fun handle(): Pair<String, Int> {
        while (true) {
            when (val key = console.reader().read()) {
                27 -> {
                    console.reader().read()
                    when (console.reader().read()) {
                        67 -> if (cursor < composing.length) cursor++
                        68 -> if (cursor > 0) cursor--
                    }
                }
                8, 127 -> {
                    if (cursor > 0) {
                        composing.deleteCharAt(cursor - 1)
                        cursor--
                    }
                }
                10, 13 -> return Pair(composing.toString(), cursor)
                else -> {
                    composing.insert(cursor, key.toChar())
                    cursor++
                }
            }
        }
    }
}
