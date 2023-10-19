package frontend

import util.Globals.Color.TRANSPARENT
import util.Globals.BackgroundColor.TRANSPARENT as BackgroundColorTRANSPARENT

class Renderer {
    private var buffer: MutableList<MutableList<MutableList<Rich>>> = mutableListOf()
    private var final: MutableList<MutableList<Rich>> = mutableListOf()
    private val contexts: MutableList<Any> = mutableListOf()

    fun stash(context: Any) {
        contexts.add(context)
    }

    private fun isTransparent(currRich: Rich): Boolean {
        return currRich.toString() == "\u2800"
    }

    private fun flatten(size: Pair<Int, Int>): String {
        val finalString = StringBuilder()
        for (scanY in 0 until size.second) {
            for (scanX in 0 until size.first) {
                finalString.append(final[scanY][scanX].toANSIString())
            }
            finalString.append("\n")
        }
        return finalString.toString()
    }

    fun render(size: Pair<Int, Int>): String {
        buffer = mutableListOf()
        final = mutableListOf()

        for (contextObject in contexts) {
            if (contextObject is Canvas) {
                buffer.addAll(contextObject.layers())
            }
        }

        for (partY in size.second downTo 1) {
            val horizontalScan = mutableListOf<Rich>()
            for (partX in size.first downTo 1) {
                var swapChar = Rich("\u2800", TRANSPARENT, BackgroundColorTRANSPARENT)
                for (layer in buffer) {
                    val currRich = layer[partY - 1][partX - 1]
                    if (!isTransparent(currRich)) {
                        swapChar = currRich
                    }
                }
                horizontalScan.add(swapChar)
            }
            horizontalScan.reverse()
            final.add(horizontalScan)
        }

        return flatten(size)
    }
}
