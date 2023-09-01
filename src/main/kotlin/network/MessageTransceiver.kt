package network

import data.SharedStore.alive
import util.Globals.MESSAGE_PORT
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

object MessageTransceiver {
    fun listenForMessages() {
        DatagramSocket(MESSAGE_PORT).use{ socket ->
            while (alive) {
                val receiveData = ByteArray(1024).let{data ->
                    DatagramPacket(data, data.size).let{
                        socket.receive(it)

                        // TODO Actually handle messsage, confirm JWT, save message to datastore/history.
                        println("Received message: ${
                            String(it.data, 0, it.length)
                        } from ${it.address.hostAddress}")
                    }
                }
            }
        }
    }

    fun sendMessage(targetAddress: String, message: String) {
        DatagramSocket().use{ socket ->
            message.toByteArray().let{data ->
                socket.send(
                    DatagramPacket(data, data.size, InetAddress.getByName(targetAddress), MESSAGE_PORT)
                )
            }
        }
    }
}