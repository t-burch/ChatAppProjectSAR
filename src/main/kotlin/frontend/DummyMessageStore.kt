package frontend

import java.util.*

object DummyMessageStore {
    private val store: HashMap<String, DummyMessageEntry> = hashMapOf(
        Pair("123456", DummyMessageEntry(5473484585, "Test Message 1 uewroiwmzu,ewlhwm4iru4wzr9,w84rmz4dw9r84w,zw94r9457439583475894375894375iuerzeoiuzoiu")),
        Pair("456436", DummyMessageEntry(5768878788, "Test Message 2")),
        Pair("584865", DummyMessageEntry(5476786586, "Test Message 3")),
        Pair("233265", DummyMessageEntry(5473468768, "Test Message 4")),
        Pair("675457", DummyMessageEntry(5473657868, "Test Message 5")),
        Pair("976977", DummyMessageEntry(5473657545, "Test Message 6")),
        Pair("124354", DummyMessageEntry(5479763223, "Test Message 7")),
        Pair("987967", DummyMessageEntry(5473854855, "Test Message 8")),
        Pair("354653", DummyMessageEntry(5473766537, "Test Message 9")),
        Pair("563475", DummyMessageEntry(5477685643, "Test Message 10")),
        Pair("678534", DummyMessageEntry(5473432568, "Test Message 11")),
        Pair("198943", DummyMessageEntry(5476865354, "Test Message 12")),
        Pair("178943", DummyMessageEntry(5476865354, "Test Message 13")),
        Pair("178923", DummyMessageEntry(5476865354, "Test Message 14")),
        Pair("238933", DummyMessageEntry(5476865354, "Test Message 15")),
        Pair("175643", DummyMessageEntry(5476865354, "Test Message 16")),
        Pair("184943", DummyMessageEntry(5476865354, "Test Message 17")),
        Pair("173843", DummyMessageEntry(5476865354, "Test Message 18")),
        Pair("178923", DummyMessageEntry(5476865354, "Test Message 19")),
        Pair("328433", DummyMessageEntry(5473658732, "Test Message 20"))
    )

    fun getAllMessages(): Map<String, DummyMessageEntry> = store.toMap()
}

data class DummyMessageEntry(
    val sent: Long,
    var message: String
)