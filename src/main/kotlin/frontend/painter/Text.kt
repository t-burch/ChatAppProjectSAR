package frontend.painter

import frontend.Rich
import util.Globals.BackgroundColor
import util.Globals.Color

class Text(
    private val text: String,
    private val color: Color,
    private val backgroundColor: BackgroundColor
) : Painter {
    private fun toRich(mod: Char): Rich {
        return Rich(mod.toString(), color, backgroundColor)
    }

    override fun paint(): List<List<Rich>> {
        val output = mutableListOf<List<Rich>>()
        val charArray = text.map { toRich(it) }.toMutableList()
        output.add(charArray)
        return output
    }
}
