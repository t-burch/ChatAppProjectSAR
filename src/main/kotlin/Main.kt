import network.ClientDiscovery.broadcast
import network.ClientDiscovery.listen
import network.DiscoveredClientsStore
import network.MessageTransceiver.listenForMessages
import network.MessageTransceiver.sendMessage
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
    thread { listenForMessages() }

    thread(start = true) {
        while (true) {
            Thread.sleep(EXPIRATION_TIME)
            // TODO Set a stale flag instead of deleting
            DiscoveredClientsStore.removeStaleEntries()

            print("\u001b[H\u001b[2J")
            println(DiscoveredClientsStore.getAllClients())
        }
    }

    thread {
        Thread.sleep(20000L)
        sendMessage("TEST MESSAGE")
    }
}

