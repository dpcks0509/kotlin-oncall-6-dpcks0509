package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.model.EmergencyWork
import oncall.utils.Validator.validateEmergencyWork

class InputView {
    fun readEmergencyWork(): EmergencyWork {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        return try {
            val emergencyWork = Console.readLine()
            validateEmergencyWork(emergencyWork)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            readEmergencyWork()
        }
    }
}