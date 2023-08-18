package frontend.painter

import frontend.Rich
import util.Globals.BackgroundColor
import util.Globals.Color

class Surface(
    text: String,
    color: Color,
    backgroundColor: BackgroundColor
) : Painter {
    private val output: List<List<Rich>>

    init {
        output = text.split(System.lineSeparator()).map{ line ->
            line.map { char ->
                Rich(char.toString(), color, backgroundColor)
            }
        }
    }

    override fun paint(): List<List<Rich>> = output
}
