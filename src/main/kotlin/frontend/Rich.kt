package frontend

import util.Globals.BackgroundColor
import util.Globals.Color

class Rich(
    private val textElement: String,
    private val color: Color,
    private val backgroundColor: BackgroundColor
) {
    fun toANSIString() = "\u001b[${color.value};${backgroundColor.value}m${textElement}\u001b[0m"

    override fun toString() = textElement
}