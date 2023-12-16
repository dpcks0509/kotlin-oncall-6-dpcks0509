package oncall.controller

import oncall.model.EmergencyWork
import oncall.view.InputView
import oncall.view.OutputView

class OnCallController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val emergencyWork = inputView.readEmergencyWorkMonthAndDay()
        readEmployees(emergencyWork)
        emergencyWork.generateSchedule()
    }

    private fun readEmployees(emergencyWork: EmergencyWork) {
        var employeesOnWeekDays = inputView.readEmployeesOnWeekDays()
        var employeesOnWeekendDays = inputView.readEmployeesOnWeekendDays(employeesOnWeekDays)
        emergencyWork.setEmployeesOnWeekDays(employeesOnWeekDays)
        emergencyWork.setEmployeesOnWeekendDays(employeesOnWeekendDays)
        if (employeesOnWeekendDays.isEmpty()) readEmployees(emergencyWork)
    }
}