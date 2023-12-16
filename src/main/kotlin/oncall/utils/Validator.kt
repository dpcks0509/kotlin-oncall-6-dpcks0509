package oncall.utils

import oncall.model.EmergencyWork
import oncall.model.Week.Companion.isDayExists
import oncall.utils.Constants.DECEMBER
import oncall.utils.Constants.JANUARY

object Validator {
    fun validateEmergencyWork(emergencyWork: String): EmergencyWork {
        val validEmergencyWork = emergencyWork.split(",")
        validateEmergencyWorkFormat(validEmergencyWork)
        val emergencyWorkMonth = validateEmergencyWorkMonth(validEmergencyWork[0])
        val emergencyWorkDay = validateEmergencyWorkDay(validEmergencyWork[1])
        return EmergencyWork(emergencyWorkMonth, emergencyWorkDay)
    }

    private fun validateEmergencyWorkFormat(emergencyWork: List<String>) {
        require(emergencyWork.size == 2) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    private fun validateEmergencyWorkMonth(month: String): Int {
        val validMonth = month.toIntOrNull() ?: 0
        require(validMonth in JANUARY..DECEMBER) { Exception.INVALID_INPUT_VALUE.getMessage() }
        return validMonth
    }

    private fun validateEmergencyWorkDay(day: String): String {
        require(isDayExists(day)) { Exception.INVALID_INPUT_VALUE.getMessage() }
        return day
    }
}