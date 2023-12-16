package oncall.controller

import oncall.view.InputView
import oncall.view.OutputView

class OnCallController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val emergencyWork = inputView.readEmergencyWork()
    }
}