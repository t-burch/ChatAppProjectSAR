import data.MessageStore
import data.SharedStore
import data.SharedStore.alive
import data.SharedStore.composingBuffer
import data.SharedStore.cursorPosition
import data.SharedStore.scrollPosition
import frontend.*
import frontend.painter.Block
import frontend.painter.Border
import frontend.painter.Surface
import frontend.painter.Text
import frontend.painter.interceptor.Alignment.VERTICAL
import frontend.painter.interceptor.ArrayAlign
import frontend.painter.interceptor.Crop
import message.MessageUtils
import network.ClientDiscovery.broadcast
import network.ClientDiscovery.listen
import network.MessageTransceiver.listenForMessages
import util.Globals
import util.Globals.BackgroundColor.BLACK
import util.Globals.Color.*
import util.JwtUtils
import java.net.InetAddress
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    MessageUtils.storeSystemMessage("You are not connected to anyone.")

    // TODO ALL THREADS: Move thread alive control into separate control instead of individual alive while condition.
    thread(start = true) {
        val inputHandler = InputHandler(99) {
            Interpreter.processInput(it)
        }
        while(alive) {
            val result = inputHandler.handle()
            composingBuffer = result.first
            cursorPosition = result.second
            scrollPosition = result.third
        }
    }

    thread(start = true) {
        while(alive) {
            Canvas(140, 33).apply{
                // Backdrop
                blit(Block(Rich("█", GRAY, BLACK), Pair(140, 33)), Pair(0, 0))
                // Chat Bar
                blit(Block(Rich("█", LIGHT_GRAY, BLACK), Pair(100, 3)), Pair(40, 0))
                // Chat Bar Border
                blit(Border(Pair(101, 3), WHITE, BLACK), Pair(39, 0))
                // Discovery Tray
                blit(Border(Pair(40, 33), WHITE, BLACK), Pair(0, 0))
                // Message History
                val messages = MessageStore.getAllMessages()
                    .entries
                    .sortedByDescending{ it.key }
                    .map{
                        when (it.value.token) {
                            "ff" -> {
                                Text(it.value.content, RED, BLACK)
                            }
                            SharedStore.identityToken -> {
                                Text(it.value.content, GRAY, BLACK)
                            }
                            else -> {
                                Text(it.value.content, WHITE, BLACK)
                            }
                        }
                    }
                blit(
                    Crop(
                        ArrayAlign(messages, 1, VERTICAL),
                        Pair(99, 28),
                        Pair(0, scrollPosition)
                    ),
                    Pair(40, 4)
                )
                // Discovery List
                /*val clients = DiscoveredClientsStore.getAllClients().map{Text(it.value.name, WHITE, BG_GRAY)}
                blit(
                    Crop(
                        ArrayAlign(clients, 1, VERTICAL),
                        Pair(38, 28),
                        Pair(0, 0)
                    ),
                    Pair(1, 2)
                )*/
                // Main Border
                blit(Border(Pair(140, 33), WHITE, BLACK), Pair(0, 0))
                // Title Text
                blit(Text("CAPSAR V1.0-Snapshot", WHITE, BLACK), Pair(60, 32))
                // Chat Message
                blit(Text(composingBuffer, Globals.Color.BLACK, Globals.BackgroundColor.WHITE), Pair(40, 1))
                // Message Cursor
                blit(Surface("‾", WHITE, BLACK), Pair(40+cursorPosition, 0))
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

    if (args.isEmpty()) {
        println("Please provide a network interface name.")
        return
    }

    val interfaceName = args[0]
    val displayName = args[1]
    thread(start = true) {
        while(alive) {
            listen()
        }
    }
    thread(start = true) {
        while(alive) {
            broadcast(interfaceName, displayName)
        }
    }
    thread(start = true) {
        while(alive) {
            listenForMessages()
        }
    }

    SharedStore.identityToken = JwtUtils.buildJwt(InetAddress.getLocalHost().hostAddress, displayName)
}

