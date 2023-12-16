package oncall

import oncall.utils.Validator.validateEmergencyWork
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnCallTest {
    @ParameterizedTest
    @ValueSource(strings = ["5,월", "1,금"])
    fun `올바른 비상 근무 배정 월, 시작 요일 입력`(emergencyWork: String) {
        assertDoesNotThrow { validateEmergencyWork(emergencyWork) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1,월", "0,금", "13,화"])
    fun `비상 근무 배정 월, 시작 요일 입력 예외 처리 (월이 1 ~ 12 사이의 숫자가 아닌 경우)`(emergencyWork: String) {
        assertThrows<IllegalArgumentException> { validateEmergencyWork(emergencyWork) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["5,monday", "1,공휴일", "3,월요일"])
    fun `비상 근무 배정 월, 시작 요일 입력 예외 처리 (시작 요일을 "일", "월", "화", "수", "목", "금", "토" 이외의 값을 입력한 경우)`(emergencyWork: String) {
        assertThrows<IllegalArgumentException> { validateEmergencyWork(emergencyWork) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["5,", ",금", "5, ", " ,금"])
    fun `비상 근무 배정 월, 시작 요일 입력 예외 처리 (월과 시작 요일을 2개 다 입력하지 않은 경우)`(emergencyWork: String) {
        assertThrows<IllegalArgumentException> { validateEmergencyWork(emergencyWork) }
    }
}