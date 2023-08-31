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

        when (alignment) {
            Alignment.HORIZONTAL -> {
                val maxRows = painters.maxOf { it.paint().size }
                for (i in 0 until maxRows) {
                    val row = mutableListOf<Rich>()
                    for (painter in painters) {
                        val currentPaint = painter.paint()
                        if (i < currentPaint.size) {
                            row.addAll(currentPaint[i])
                        }
                        if (painter != painters.last()) {
                            repeat(margin) { row.add(Rich(" ", TRANSPARENT, BG_TRANSPARENT)) }
                        }
                    }
                    alignedOutput.add(row)
                }
            }

            Alignment.VERTICAL -> {
                for (painter in painters) {
                    alignedOutput.addAll(painter.paint())
                    if (painter != painters.last()) {
                        repeat(margin) { alignedOutput.add(listOf(Rich(" ", TRANSPARENT, BG_TRANSPARENT))) }
                    }
                }
            }
        }

        return alignedOutput
    }
}
