package frontend.painter

import frontend.Rich

class Block(private val blockChar: Rich, private val size: Pair<Int, Int>) : Painter {
    override fun paint(): List<List<Rich>> {
        val blockLine = List(size.first) { blockChar }
        return List(size.second) { blockLine }
    }
}
