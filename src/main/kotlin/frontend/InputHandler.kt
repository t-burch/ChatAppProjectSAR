package frontend

import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader
import kotlin.system.exitProcess

class InputHandler(private val charLimit: Int) {
    private val terminal: Terminal = TerminalBuilder.builder()
        .system(true)
        .build()

    private val reader: NonBlockingReader = terminal.reader()

    private val composing = StringBuilder()
    private var cursor = 0

    init {
        terminal.enterRawMode()
    }

    fun handle(): Pair<String, Int> {
        when (val read = reader.read(100)) {
            3 -> { // CTRL+C
                close()
                exitProcess(0)
            }
            27 -> {
                reader.read()
                when (reader.read()) {
                    65 -> { // Arrow Up
                        // Placeholder for Arrow Up action
                    }
                    66 -> { // Arrow Down
                        // Placeholder for Arrow Down action
                    }
                    67 -> if (cursor < composing.length) cursor++ // Right arrow
                    68 -> if (cursor > 0) cursor-- // Left arrow
                }
            }
            8, 127 -> { // Backspace/Delete
                if (cursor > 0) {
                    composing.deleteCharAt(cursor - 1)
                    cursor--
                }
            }
            -2 -> {
                return Pair(composing.toString(), cursor) // Return early for timeout without processing
            }
            else -> { // Other characters
                if (composing.length < 99) {
                    composing.insert(cursor, read.toChar())
                    cursor++
                }
            }
        }

        return Pair(composing.toString(), cursor)
    }

    private fun close() {
        reader.close()
        terminal.close()
    }
}
