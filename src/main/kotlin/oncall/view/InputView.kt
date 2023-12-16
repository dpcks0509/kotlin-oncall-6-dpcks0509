package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.model.EmergencyWork
import oncall.utils.Validator.validateEmergencyWork
import oncall.utils.Validator.validateEmployeesOnWeekDays
import oncall.utils.Validator.validateEmployeesOnWeekendDays

class InputView {
    fun readEmergencyWorkMonthAndDay(): EmergencyWork {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        return try {
            val emergencyWork = Console.readLine()
            validateEmergencyWork(emergencyWork)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readEmergencyWorkMonthAndDay()
        }
    }

    fun readEmployeesOnWeekDays(): List<String> {
        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return try {
            val employeesOnWeekDays = Console.readLine()
            validateEmployeesOnWeekDays(employeesOnWeekDays)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readEmployeesOnWeekDays()
        }
    }

    fun readEmployeesOnWeekendDays(employeesOnWeekDays: List<String>): List<String> {
        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        return try {
            val employeesOnWeekendDays = Console.readLine()
            validateEmployeesOnWeekendDays(employeesOnWeekendDays, employeesOnWeekDays)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            emptyList()
        }
    }
}