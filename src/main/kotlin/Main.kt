import frontend.*
import frontend.painter.Block
import frontend.painter.Border
import frontend.painter.Surface
import frontend.painter.Text
import util.Globals
import util.Globals.BackgroundColor.BLACK
import util.Globals.BackgroundColor.RED
import util.Globals.Color.TRANSPARENT
import util.Globals.Color.WHITE
import java.awt.Color.red
import kotlin.concurrent.thread

@Volatile var composing = ""
@Volatile var cursorPosition = 0

fun main(args: Array<String>) {
    thread(start = true) {
        val inputHandler = InputHandler()
        while(true) {
            val result = inputHandler.handle()
            composing = result.first
            cursorPosition = result.second
        }
    }

    thread(start = true) {
        while(true) {
            Canvas(140, 33).apply{
                // Chat Bar
                blit(Block(Rich("█", WHITE, BLACK), Pair(100, 3)), Pair(40, 0))
                // Chat Bar Border
                blit(Border(Pair(101, 3), WHITE, BLACK), Pair(39, 0))
                // Discovery Tray
                blit(Border(Pair(40, 33), WHITE, BLACK), Pair(0, 0))
                // Main Border
                blit(Border(Pair(140, 33), WHITE, BLACK), Pair(0, 0))
                // Title Text
                blit(Text("CAPSAR V1.0-Snapshot", WHITE, BLACK), Pair(60, 32))
                // Chat Message
                blit(Text(composing, Globals.Color.BLACK, Globals.BackgroundColor.WHITE), Pair(40, 1))
                // Message Cursor
                blit(Surface("‾", TRANSPARENT, RED), Pair(40+cursorPosition, 0))
            }.let {
                Renderer().apply {
                    stash(it)
                }.let { r ->
                    Console.put(r.render(Pair(it.size.first, it.size.second)))
                    // FPS Limiter
                    Console.throttle(15)
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

