package oncall.utils

enum class Exception(private val message: String) {
    INVALID_INPUT_VALUE("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    fun getMessage() = "[ERROR] $message"
}