package oncall.model

class EmergencyWork(private val month: Int, private val day: String) {
    private val employeesOnWeekDays = mutableListOf<String>()
    private val employeesOnWeekendDays = mutableListOf<String>()

    fun setEmployeesOnWeekDays(employees: List<String>) {
        employeesOnWeekDays.clear()
        employeesOnWeekDays.addAll(employees)
    }

    fun setEmployeesOnWeekendDays(employees: List<String>) {
        employeesOnWeekendDays.clear()
        employeesOnWeekendDays.addAll(employees)
    }

    fun getEmployeesOnWeekDays() = employeesOnWeekDays
    fun getEmployeesOnWeekendDays() = employeesOnWeekendDays
}