package util

object Globals {
    const val EXPIRATION_TIME = 10000L
    const val JWT_SIGNING_KEY = "superCoolJWTSigningKeyThatHasEnoughBitsProbablyHopefullyYesYes"
    const val BROADCAST_PORT = 9876
    const val BROADCAST_COOLDOWN = 2000L
    const val MESSAGE_PORT = 9999

    enum class Color(val value: Int) {
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        MAGENTA(35),
        CYAN(36),
        WHITE(97),
        GRAY(90),
        LIGHT_RED(91),
        LIGHT_GREEN(92),
        LIGHT_YELLOW(93),
        LIGHT_BLUE(94),
        LIGHT_MAGENTA(95),
        LIGHT_CYAN(96),
        LIGHT_GRAY(37),
        TRANSPARENT(0)
    }
    enum class BackgroundColor(val value: Int) {
        BLACK(40),
        RED(41),
        GREEN(42),
        YELLOW(43),
        BLUE(44),
        MAGENTA(45),
        CYAN(46),
        WHITE(47),
        GRAY(100),
        LIGHT_RED(101),
        LIGHT_GREEN(102),
        LIGHT_YELLOW(103),
        LIGHT_BLU(104),
        LIGHT_MAGENTA(105),
        LIGHT_CYAN(106),
        LIGHT_GRAY(107),
        TRANSPARENT(0)
    }
}