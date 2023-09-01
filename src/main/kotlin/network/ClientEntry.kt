package network

data class ClientEntry(
    private var name: String,
    private var lastSeen: Long,
    private var stale: Boolean,
    private var address: String
)