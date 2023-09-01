package network

import data.DiscoveredClientsStore
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

object MessageTransceiver {
    private const val MESSAGE_PORT = 9999

    fun listenForMessages() {
        DatagramSocket(MESSAGE_PORT).use { socket ->
            while (true) {
                val receiveData = ByteArray(1024)
                val packet = DatagramPacket(receiveData, receiveData.size)
                socket.receive(packet)

                val message = String(packet.data, 0, packet.length)
                println("Received message: $message from ${packet.address.hostAddress}")
            }
        }
    }

    fun sendMessage(message: String) {
        val targetAddress = DiscoveredClientsStore.getAllClients().keys.firstOrNull() ?: run{
            println("No clients discovered to send message to!")
            return
        }

        DatagramSocket().use{ socket ->
            val sendData = message.toByteArray()
            val packet = DatagramPacket(sendData, sendData.size, InetAddress.getByName(targetAddress), MESSAGE_PORT)
            socket.send(packet)
        }
    }
}