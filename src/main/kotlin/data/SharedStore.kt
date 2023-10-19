package data

object SharedStore {
    @Volatile
    var composingBuffer: String = ""
    @Volatile
    var cursorPosition: Int = 0
    @Volatile
    var scrollPosition: Int = 0
    @Volatile
    var selectedTargetJwt: String = "null"
    @Volatile
    var alive: Boolean = true
    @Volatile
    var identityToken: String = "0"
}