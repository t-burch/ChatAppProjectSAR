import frontend.Canvas
import frontend.Console
import frontend.Renderer
import network.ClientDiscovery.broadcast
import network.ClientDiscovery.listen
import network.DiscoveredClientsStore
import network.MessageTransceiver.listenForMessages
import network.MessageTransceiver.sendMessage
import util.Globals
import util.Globals.BackgroundColor.BLACK
import util.Globals.Color.WHITE
import util.Globals.EXPIRATION_TIME
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    thread(start = true) {
        Canvas(100, 14).apply{
            blit(frontend.painter.Text("Test", WHITE, BLACK), Pair(0, 0))
        }.let{
            Renderer().apply{
                stash(it)
            }.let{ r ->
                while(true) {
                    Console.put(r.render(Pair(it.size.first, it.size.second)))
                    Console.throttle(30)
                }
            }
        }
    }

    /*
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
    */
}

