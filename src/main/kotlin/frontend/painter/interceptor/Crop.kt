package frontend.painter.interceptor

import frontend.Rich
import frontend.painter.Painter

class Crop(
    private val painter: Painter,
    private val size: Pair<Int, Int>,
    private val offset: Pair<Int, Int>
) : Interceptor(painter) {

    override fun paint(): List<List<Rich>> {
        val originalOutput = painter.paint()

        // Ensure that not cropping outside of bounds
        if (offset.first + size.first > originalOutput.size
            || offset.second + size.second > originalOutput[0].size) {
            throw IllegalArgumentException("Attempting to crop outside of original dimensions.")
        }

        val croppedOutput = mutableListOf<List<Rich>>()

        for (i in offset.first until offset.first + size.first) {
            val row = originalOutput[i].subList(offset.second, offset.second + size.second)
            croppedOutput.add(row)
        }

        return croppedOutput
    }
}
