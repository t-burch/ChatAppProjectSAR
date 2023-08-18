package frontend

import util.Globals
import util.Globals.Color.TRANSPARENT
import kotlin.collections.MutableList
import kotlin.collections.List
import util.Globals.BackgroundColor.TRANSPARENT as BackgroundColorTRANSPARENT

class Canvas(
    canvasWidth: Int,
    canvasHeight: Int
) {
    private val store: MutableList<MutableList<MutableList<Rich>>> = mutableListOf()
    private val size = Pair(canvasWidth, canvasHeight)

    fun blit(
        layer: List<List<Rich>>,
        locator: Pair<Int, Int>
    ) {
        val result = mutableListOf<MutableList<Rich>>()
        val spacer = Rich("\u2800", TRANSPARENT, BackgroundColorTRANSPARENT)
        val hFinalSpacer = List(size.first){ spacer }

        result.addAll(List(locator.second) { hFinalSpacer.toMutableList() })

        for (row in layer) {
            val hSpacer = MutableList(locator.first) { spacer }
            hSpacer.addAll(row)
            hSpacer.addAll(List(size.first - (row.size + locator.first)) { spacer })
            result.add(hSpacer)
        }

        result.addAll(List(size.second - result.size) { hFinalSpacer.toMutableList() })

        store.add(result)
    }

    fun layers() = store
}
