package network

data class ClientEntry(
    val name: String,
    val lastSeen: Long,
    val stale: Boolean,
    val address: String,
    val token: String
)