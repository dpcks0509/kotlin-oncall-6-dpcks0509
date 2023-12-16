package oncall.model

enum class Week(val day: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    companion object {
        fun isDayExists(day: String): Boolean {
            return values().any { week -> week.day == day }
        }

        fun getDayOfOrdinal(day: String): Int {
            return values().first { it.day == day }.ordinal
        }

        fun getDayByOrdinal(ordinal: Int): String {
            return values().first { it.ordinal == ordinal }.day
        }
    }
}