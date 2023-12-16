package oncall.model

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
}
