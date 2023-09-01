package data

import network.ClientEntry

object DiscoveredClientsStore {
    @Volatile
    private var store: MutableMap<String, ClientEntry> = HashMap()

    fun addOrUpdateClient(ip: String, name: String) {
        store[ip] = ClientEntry(name, System.currentTimeMillis(), false, ip)
    }

    fun getAllClients(): Map<String, ClientEntry> = store.toMap()
}

