package frontend.painter.interceptor

import frontend.Rich
import frontend.painter.Painter
import util.Globals.Color.TRANSPARENT
import util.Globals.BackgroundColor.TRANSPARENT as BG_TRANSPARENT

class Crop(
    private val painter: Painter,
    private val size: Pair<Int, Int>,
    private val offset: Pair<Int, Int>
) : Interceptor(painter) {

    override fun paint(): List<List<Rich>> {
        val originalOutput = painter.paint()

        val croppedOutput = mutableListOf<List<Rich>>()

        for (i in 0 until size.first) {
            val rowIndex = i + offset.first

            if (rowIndex < originalOutput.size) {
                val row = if (offset.second + size.second <= originalOutput[rowIndex].size) {
                    originalOutput[rowIndex].subList(offset.second, offset.second + size.second)
                } else {
                    val paddedRow = originalOutput[rowIndex].toMutableList()
                    while (paddedRow.size < offset.second + size.second) {
                        paddedRow.add(Rich("⠀", TRANSPARENT, BG_TRANSPARENT))
                    }
                    paddedRow.subList(offset.second, offset.second + size.second)
                }
                croppedOutput.add(row)
            } else {
                croppedOutput.add(List(size.second) { Rich("⠀", TRANSPARENT, BG_TRANSPARENT) })
            }
        }

        return croppedOutput
    }
}
