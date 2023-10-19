package data

import network.ClientEntry
import util.JwtUtils

object DiscoveredClientsStore {
    @Volatile
    private var store: MutableMap<String, ClientEntry> = HashMap()

    fun addOrUpdateClient(jwt: String) {
        JwtUtils.getJWTClaims(jwt).let{
            store[jwt] = ClientEntry(it["name"] as String, System.currentTimeMillis(), false, it.subject, jwt)
        }
    }

    fun getAllClients(): Map<String, ClientEntry> = store.toMap()
}

