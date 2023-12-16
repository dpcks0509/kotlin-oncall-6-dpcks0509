package oncall

import oncall.model.EmergencyWork
import oncall.utils.Validator.validateEmergencyWork
import oncall.utils.Validator.validateEmployeesOnWeekDays
import oncall.utils.Validator.validateEmployeesOnWeekendDays
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

    @ParameterizedTest
    @ValueSource(strings = ["준팍,도밥,고니,수아,루루,글로", "수아,루루,글로,솔로스타,우코,슬링키"])
    fun `올바른 비상 근무 순번 사원 닉네임 입력`(employees: String) {
        assertDoesNotThrow { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["준팍 ,도밥,고니,수아,루루,글로", "수아, 루루,글로,솔로스타,우코,슬링키"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (콤마 앞뒤로 공백이 존재하는 경우)`(employees: String) {
        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = [",도밥,고니,수아,루루,글로", " ,루루,글로,솔로스타,우코,슬링키"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (사원 닉네임이 공백일 경우)`(employees: String) {
        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["준팍,도밥,고니,수아,루루,준팍", "수아,수아,글로,솔로스타,우코,슬링키"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (사원 닉네임이 중복되는 경우)`(employees: String) {
        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["크리스티아누호날두,도밥,고니,수아,루루,준팍", "수아,글로,모하메드살라,솔로스타,우코,슬링키"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (사원 닉네임의 길이가 5자를 초과하는 경우)`(employees: String) {
        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["준팍,도밥,고니,수아", "가가,가가가,가가가가,가가가가가,나나,나나,나나나나,나나나나나,다다,다다다,다다다다,다다다다다,라라,라라라,라라라라,라라라라라,마마,마마마,마마마마,마마마마마,바바,바바바,바바바바,바바바바바,사사,사사사,사사사사,사사사사사,아아,아아아,아아아아,아아아아아,자자,자자자,자자자자,자자자자자"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (사원 인원이 5 ~ 35 명 사이가 아닌 경우)`(employees: String) {
        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekDays(employees) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["도깨비,준팍,고니,루루,글로,수아", "고양이,글로,준팍,루루,도밥,수아"])
    fun `비상 근무 순번 사원 닉네임 입력 예외 처리 (평일 비상 근무에 입력된 사원이 휴일 비상 근무에 입력되지 않은 경우)`(employees: String) {
        val employeesOnWeekendDays = listOf("준팍", "도밥", "고니", "수아", "루루", "글로")

        assertThrows<IllegalArgumentException> { validateEmployeesOnWeekendDays(employees, employeesOnWeekendDays) }
    }
}