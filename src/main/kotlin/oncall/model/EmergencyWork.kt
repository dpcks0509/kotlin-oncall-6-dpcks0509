package oncall.model

import oncall.model.Calendar.Companion.getEndDay
import oncall.model.Calendar.Companion.getTypeOfDay
import oncall.utils.Constants.HOLIDAY
import oncall.utils.Constants.START_DAY
import oncall.utils.Constants.WEEKEND_DAY
import oncall.utils.Constants.WEEK_DAY
import oncall.utils.Constants.WEEK_DAY_AND_HOLIDAY

class EmergencyWork(private val month: Int, private val day: String) {
    private val employeesOnWeekDays = mutableListOf<String>()
    private val employeesOnWeekendDays = mutableListOf<String>()
    private val schedule = LinkedHashMap<Int, String>()

    fun setEmployeesOnWeekDays(employees: List<String>) {
        employeesOnWeekDays.clear()
        employeesOnWeekDays.addAll(employees)
    }

    fun setEmployeesOnWeekendDays(employees: List<String>) {
        employeesOnWeekendDays.clear()
        employeesOnWeekendDays.addAll(employees)
    }

    fun generateSchedule() {
        val endDay = getEndDay(month)
        for (dayOfMonth in START_DAY..endDay) {
            val typeOfDay = getTypeOfDay(month, day, dayOfMonth)
            when {
                typeOfDay == WEEK_DAY -> putScheduleOnWeekDay(dayOfMonth)
                typeOfDay == WEEKEND_DAY || typeOfDay == WEEK_DAY_AND_HOLIDAY || typeOfDay == HOLIDAY ->
                    putScheduleOnWeekendDay(dayOfMonth)
            }
        }
    }

    private fun putScheduleOnWeekDay(dayOfMonth: Int) {
        var firstEmployee = employeesOnWeekDays[0]

        if (schedule.size != 0 && schedule[dayOfMonth - 1] == firstEmployee) {
            val temp = employeesOnWeekDays[0]
            employeesOnWeekDays[0] = employeesOnWeekDays[1]
            employeesOnWeekDays[1] = temp
        }

        firstEmployee = employeesOnWeekDays[0]
        employeesOnWeekDays.add(employeesOnWeekDays.removeAt(0))
        schedule[dayOfMonth] = firstEmployee
    }

    private fun putScheduleOnWeekendDay(dayOfMonth: Int) {
        var firstEmployee = employeesOnWeekendDays[0]

        if (schedule.size != 0 && schedule[dayOfMonth - 1] == firstEmployee) {
            val temp = employeesOnWeekendDays[0]
            employeesOnWeekendDays[0] = employeesOnWeekendDays[1]
            employeesOnWeekendDays[1] = temp
        }

        firstEmployee = employeesOnWeekendDays[0]
        employeesOnWeekendDays.add(employeesOnWeekendDays.removeAt(0))
        schedule[dayOfMonth] = firstEmployee
    }

    fun getMonth() = month
    fun getDay() = day
    fun getSchedule() = schedule
}