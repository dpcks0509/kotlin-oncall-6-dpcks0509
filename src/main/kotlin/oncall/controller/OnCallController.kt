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
        generateEmergencyWorkSchedule(emergencyWork)
    }

    private fun readEmployees(emergencyWork: EmergencyWork) {
        val employeesOnWeekDays = inputView.readEmployeesOnWeekDays()
        val employeesOnWeekendDays = inputView.readEmployeesOnWeekendDays(employeesOnWeekDays)
        emergencyWork.setEmployeesOnWeekDays(employeesOnWeekDays)
        emergencyWork.setEmployeesOnWeekendDays(employeesOnWeekendDays)
        if (employeesOnWeekendDays.isEmpty()) readEmployees(emergencyWork)
    }

    private fun generateEmergencyWorkSchedule(emergencyWork: EmergencyWork) {
        emergencyWork.generateSchedule()
        outputView.printEmergencyWorkSchedule(emergencyWork)
    }
}