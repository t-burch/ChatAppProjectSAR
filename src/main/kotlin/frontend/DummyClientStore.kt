package frontend

object DummyClientStore {
    private val store: HashMap<String, DummyClientEntry> = hashMapOf(
        Pair("123456", DummyClientEntry("Test Client 1", 4357477426375)),
        Pair("274373", DummyClientEntry("Test Client 2", 4357477435433)),
        Pair("853368", DummyClientEntry("Test Client 3", 4357477669457)),
        Pair("896515", DummyClientEntry("Test Client 4", 4357477658656))
    )

    fun getAllClients(): Map<String, DummyClientEntry> = store.toMap()
}

data class DummyClientEntry(
    val name: String,
    var lastSeen: Long
)