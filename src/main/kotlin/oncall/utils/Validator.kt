package oncall.utils

import oncall.model.EmergencyWork
import oncall.model.Week.Companion.isDayExists
import oncall.utils.Constants.DECEMBER
import oncall.utils.Constants.JANUARY
import oncall.utils.Constants.MAX_EMPLOYEES_SIZE
import oncall.utils.Constants.MAX_EMPLOYEE_NAME_LENGTH
import oncall.utils.Constants.MIN_EMPLOYEES_SIZE

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

    fun validateEmployeesOnWeekDays(employees: String): List<String> {
        val validEmployees = employees.split(",").map { employeeName -> validateEmployeeName(employeeName) }
        validateEmployeesDuplicate(validEmployees)
        validateEmployeesSize(validEmployees)
        return validEmployees
    }

    private fun validateEmployeeName(employeeName: String): String {
        validateEmployeeNameBlank(employeeName)
        validateEmployeeNameFormat(employeeName)
        validateEmployeeNameLength(employeeName)
        return employeeName
    }

    private fun validateEmployeeNameBlank(employeeName: String) {
        require(employeeName.isNotBlank()) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    private fun validateEmployeeNameFormat(employeeName: String) {
        require(employeeName[0] != ' ' && employeeName[employeeName.length - 1] != ' ') { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    private fun validateEmployeeNameLength(employeeName: String) {
        require(employeeName.length <= MAX_EMPLOYEE_NAME_LENGTH) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    private fun validateEmployeesDuplicate(employees: List<String>) {
        require(employees.size == employees.toSet().size) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    private fun validateEmployeesSize(employees: List<String>) {
        require(employees.size in MIN_EMPLOYEES_SIZE..MAX_EMPLOYEES_SIZE) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }

    fun validateEmployeesOnWeekendDays(
        employeesOnWeekendDays: String,
        employeesOnWeekDays: List<String>
    ): List<String> {
        val validEmployees =
            employeesOnWeekendDays.split(",").map { employeeName -> validateEmployeeName(employeeName) }
        validateEmployeesDuplicate(validEmployees)
        validateEmployeesSize(validEmployees)
        validateEmployeesEqualWeekDaysAndWeekendDays(validEmployees, employeesOnWeekDays)
        return validEmployees
    }

    private fun validateEmployeesEqualWeekDaysAndWeekendDays(
        employeesOnWeekendDays: List<String>,
        employeesOnWeekDays: List<String>
    ) {
        require(employeesOnWeekendDays.all { employeeOnWeekendDays -> employeesOnWeekDays.contains(employeeOnWeekendDays) } && employeesOnWeekendDays.size == employeesOnWeekDays.size) { Exception.INVALID_INPUT_VALUE.getMessage() }
    }
}