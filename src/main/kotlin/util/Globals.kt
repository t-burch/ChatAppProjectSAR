package util

object Globals {
    const val EXPIRATION_TIME = 10000L

    enum class Color(value: Int) {
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        MAGENTA(35),
        CYAN(36),
        WHITE(37),
        GRAY(90),
        LIGHT_RED(91),
        LIGHT_GREEN(92),
        LIGHT_YELLOW(93),
        LIGHT_BLUE(94),
        LIGHT_MAGENTA(95),
        LIGHT_CYAN(96),
        LIGHT_GRAY(97),
        TRANSPARENT(0)
    }
    enum class BackgroundColor(value: Int) {
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