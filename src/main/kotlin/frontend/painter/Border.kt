package frontend.painter

import frontend.Rich
import util.Globals.BackgroundColor
import util.Globals.Color

class Border(
    private val size: Pair<Int, Int>,
    private val color: Color,
    private val backgroundColor: BackgroundColor
) : Painter {
    override fun paint(): List<List<Rich>> {
        val (width, height) = size
        val output = mutableListOf<List<Rich>>()

        for (y in 0 until height) {
            val row = mutableListOf<Rich>()
            for (x in 0 until width) {
                when {
                    x == 0 && y == 0 -> row.add(Rich("└", color, backgroundColor))
                    x == width - 1 && y == 0 -> row.add(Rich("┘", color, backgroundColor))
                    x == 0 && y == height - 1 -> row.add(Rich("┌", color, backgroundColor))
                    x == width - 1 && y == height - 1 -> row.add(Rich("┐", color, backgroundColor))
                    y == 0 || y == height - 1 -> row.add(Rich("─", color, backgroundColor))
                    x == 0 || x == width - 1 -> row.add(Rich("│", color, backgroundColor))
                    else -> row.add(Rich("⠀", color, backgroundColor))
                }
            }
            output.add(row)
        }
        return output
    }
}

