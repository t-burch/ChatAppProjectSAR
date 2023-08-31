package frontend.painter.interceptor

import frontend.Rich
import frontend.painter.Painter
import util.Globals.Color.TRANSPARENT
import util.Globals.BackgroundColor.TRANSPARENT as BG_TRANSPARENT

enum class Alignment {
    HORIZONTAL, VERTICAL
}

class ArrayAlign(
    private val painters: List<Painter>,
    private val margin: Int,
    private val alignment: Alignment
) : MultiInterceptor(painters) {

    override fun paint(): List<List<Rich>> {
        val alignedOutput = mutableListOf<List<Rich>>()

        val maxLength = when (alignment) {
            Alignment.HORIZONTAL -> painters.flatMap { it.paint() }.maxOf { it.size }
            Alignment.VERTICAL -> painters.maxOf { it.paint().maxOf { line -> line.size } }
        }

        when (alignment) {
            Alignment.HORIZONTAL -> {
                val maxRows = painters.maxOf { it.paint().size }
                for (i in 0 until maxRows) {
                    val row = mutableListOf<Rich>()
                    for (painter in painters) {
                        val currentPaint = painter.paint()
                        if (i < currentPaint.size) {
                            row.addAll(padLineToMax(currentPaint[i], maxLength))
                        } else {
                            row.addAll(padLineToMax(emptyList(), maxLength))
                        }
                        if (painter != painters.last()) {
                            repeat(margin) { row.add(Rich("⠀", TRANSPARENT, BG_TRANSPARENT)) }
                        }
                    }
                    alignedOutput.add(row)
                }
            }

            Alignment.VERTICAL -> {
                for (painter in painters) {
                    for (line in painter.paint()) {
                        alignedOutput.add(padLineToMax(line, maxLength))
                    }
                    if (painter != painters.last()) {
                        repeat(margin) { alignedOutput.add(padLineToMax(emptyList(), maxLength)) }
                    }
                }
            }
        }

        return alignedOutput
    }

    private fun padLineToMax(line: List<Rich>, maxLength: Int): List<Rich> {
        return if (line.size < maxLength) {
            val padded = line.toMutableList()
            repeat(maxLength - line.size) {
                padded.add(Rich("⠀", TRANSPARENT, BG_TRANSPARENT))
            }
            padded
        } else {
            line
        }
    }
}
