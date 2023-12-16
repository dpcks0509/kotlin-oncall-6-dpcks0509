package oncall.view

import oncall.model.Calendar.Companion.getEndDay
import oncall.model.Calendar.Companion.getTypeOfDay
import oncall.model.EmergencyWork
import oncall.model.Week.Companion.getDayByOrdinal
import oncall.model.Week.Companion.getDayOfOrdinal
import oncall.utils.Constants.WEEK_DAY_AND_HOLIDAY

class OutputView {
    fun printEmergencyWorkSchedule(emergencyWork: EmergencyWork) {
        val month = emergencyWork.getMonth()
        val endDay = getEndDay(month)
        for (dayOfMonth in 1..endDay) {
            var day = getDayByOrdinal((getDayOfOrdinal(emergencyWork.getDay()) + dayOfMonth - 1) % 7)
            if (getTypeOfDay(month, day, dayOfMonth) == WEEK_DAY_AND_HOLIDAY) day = "${day}(휴일)"
            println("${month}월 ${dayOfMonth}일 $day ${emergencyWork.getSchedule()[dayOfMonth]}")
        }
    }
}