package network

import util.Globals.EXPIRATION_TIME
import java.util.concurrent.ConcurrentHashMap

object DiscoveredClientsStore {
    private val store: ConcurrentHashMap<String, ClientEntry> = ConcurrentHashMap()

    fun addOrUpdateClient(ip: String, name: String) {
        store[ip] = ClientEntry(name, System.currentTimeMillis())
    }

    fun removeStaleEntries() {
        val currentTime = System.currentTimeMillis()
        store.entries.removeIf { currentTime - it.value.lastSeen > EXPIRATION_TIME }
    }

    fun getAllClients(): Map<String, ClientEntry> = store.toMap()
}

data class ClientEntry(
    val name: String,
    var lastSeen: Long
)