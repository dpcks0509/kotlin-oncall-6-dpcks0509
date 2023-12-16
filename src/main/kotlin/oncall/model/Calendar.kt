package oncall.model

import oncall.model.Week.Companion.getDayOfOrdinal
import oncall.utils.Constants.HOLIDAY
import oncall.utils.Constants.WEEKEND_DAY
import oncall.utils.Constants.WEEK_DAY
import oncall.utils.Constants.WEEK_DAY_AND_HOLIDAY

enum class Calendar(private val month: Int, private val endDay: Int, private val holidays: List<Int>) {
    JANUARY(1, 31, listOf(1)),
    FEBRUARY(2, 28, emptyList()),
    MARCH(3, 31, listOf(1)),
    APRIL(4, 30, emptyList()),
    MAY(5, 31, listOf(5)),
    JUNE(6, 30, listOf(6)),
    JULY(7, 31, emptyList()),
    AUGUST(8, 31, listOf(15)),
    SEPTEMBER(9, 30, emptyList()),
    OCTOBER(10, 31, listOf(3, 9)),
    NOVEMBER(11, 30, emptyList()),
    DECEMBER(12, 31, listOf(25));

    companion object {
        fun getEndDay(month: Int): Int {
            return values().first { it.month == month }.endDay
        }

        fun getTypeOfDay(month: Int, day: String, dayOfMonth: Int): Int {
            val weekDays = listOf(0, 1, 2, 3, 4)
            val weekendDays = listOf(5, 6)
            val holidays = values().first { it.month == month }.holidays
            val dayOfOrdinal = (getDayOfOrdinal(day) + dayOfMonth - 1) % 7
            return when {
                weekDays.contains(dayOfOrdinal) && holidays.contains(dayOfMonth) -> WEEK_DAY_AND_HOLIDAY
                weekendDays.contains(dayOfOrdinal) -> WEEKEND_DAY
                weekDays.contains(dayOfOrdinal) -> WEEK_DAY
                else -> HOLIDAY
            }
        }
    }
}
