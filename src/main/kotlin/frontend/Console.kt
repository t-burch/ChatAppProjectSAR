package frontend

object Console {
    fun put(text: String) {
        print("\u001b[H\u001b[2J")
        println("\u001b[3J")
        print(text)
    }

    fun throttle(delta: Int) {
        Thread.sleep((1000 / delta).toLong())
    }
}
