package network

import data.SharedStore.alive
import data.DiscoveredClientsStore
import util.Globals.BROADCAST_PORT
import util.Globals.BROADCAST_COOLDOWN
import util.JwtUtils
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress.getLocalHost
import java.net.NetworkInterface

object ClientDiscovery {
    fun broadcast(interfaceName: String, displayName: String) {
            DatagramSocket().use{ socket ->
                socket.apply{
                    broadcast = true
                }.let {
                    NetworkInterface.getByName(interfaceName).let{ ifc ->
                        if (ifc == null) {
                            println("Error: Network interface $interfaceName not found.")
                            listAllInterfaces()
                            return
                        }

                        ifc.interfaceAddresses.mapNotNull{
                            it.broadcast
                        }.let{
                            while (alive) {
                                JwtUtils.buildJwt(getLocalHost().hostAddress, displayName).toByteArray().let{ data ->
                                    for (address in it) {
                                        socket.send(DatagramPacket(data, data.size, address, BROADCAST_PORT))
                                    }
                                }

                                Thread.sleep(BROADCAST_COOLDOWN)
                            }
                        }
                    }
                }
            }
        }

    // TODO Use for something more intelligent
    private fun listAllInterfaces() {
        println("Available network interfaces are:")
        NetworkInterface.getNetworkInterfaces().asSequence().forEach{
            println("Name: ${it.name} - Display name: ${it.displayName}")
        }
    }

    fun listen() {
        DatagramSocket(BROADCAST_PORT).use{ socket ->
            while (alive) {
                ByteArray(1024).let{ dat ->
                    DatagramPacket(dat, dat.size).let{
                        socket.receive(it)
                        DiscoveredClientsStore.addOrUpdateClient(String(it.data, 0, it.length))
                    }
                }
            }
        }
    }
}