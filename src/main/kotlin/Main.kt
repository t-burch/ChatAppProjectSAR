import network.ClientDiscovery.broadcast
import network.ClientDiscovery.listen
import network.DiscoveredClientsStore
import util.Globals.EXPIRATION_TIME
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide a network interface name.")
        return
    }

    val interfaceName = args[0]
    val displayName = args[1]
    thread { listen() }
    thread { broadcast(interfaceName, displayName) }

    thread(start = true) {
        while (true) {
            Thread.sleep(EXPIRATION_TIME)
            DiscoveredClientsStore.removeStaleEntries()

            print("\u001b[H\u001b[2J")
            println(DiscoveredClientsStore.getAllClients())
        }
    }
}

