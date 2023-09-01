package network

import data.DiscoveredClientsStore
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress.getLocalHost
import java.net.NetworkInterface
import java.util.*

object ClientDiscovery {
        private const val SECRET_KEY = "superCoolJWTSigningKeyThatHasEnoughBitsProbablyHopefullyYesYes"
        private const val BROADCAST_PORT = 9876
        private const val SLEEP_DURATION = 2000L

        private fun getHostAddress() = getLocalHost().hostAddress

        private fun createJWT(displayName: String) = Date().let {
            Jwts.builder()
                .setSubject(getHostAddress())
                .setIssuedAt(it)
                .claim("name", displayName)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray()))
                .compact()
        }

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
                            while (true) {
                                val sendData = createJWT(displayName).toByteArray()

                                for (address in it) {
                                    val packet = DatagramPacket(sendData, sendData.size, address, BROADCAST_PORT)
                                    socket.send(packet)
                                }

                                Thread.sleep(SLEEP_DURATION)
                            }
                        }
                    }
                }
            }
        }

        private fun listAllInterfaces() {
            println("Available network interfaces are:")
            NetworkInterface.getNetworkInterfaces().asSequence().forEach{
                println("Name: ${it.name} - Display name: ${it.displayName}")
            }
        }

    fun listen() {
        DatagramSocket(BROADCAST_PORT).use { socket ->
            while (true) {
                val receiveData = ByteArray(1024)
                val packet = DatagramPacket(receiveData, receiveData.size)
                socket.receive(packet)

                try {
                    val claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY.toByteArray())
                        .build()
                        .parseClaimsJws(String(packet.data, 0, packet.length))
                        .body

                    val ip = claims.subject
                    val name = claims["name"] as String

                    DiscoveredClientsStore.addOrUpdateClient(ip, name)
                } catch (e: Exception) {
                    println("Error parsing JWT: ${e.message}")
                }
            }
        }
    }
}