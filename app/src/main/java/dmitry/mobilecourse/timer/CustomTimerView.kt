package dmitry.mobilecourse.timer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dmitry.mobilecourse.databinding.CustomTimerViewBinding
import kotlinx.coroutines.delay
import kotlin.properties.Delegates

class CustomTimerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var binding = CustomTimerViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    var isRunning: Boolean = false
        private set

    private var hours: Int by Delegates.observable(0) { _, _, new -> binding.hoursTextView.text = timeMetricToString(new)}
    private var minutes: Int by Delegates.observable(0) { _, _, new -> binding.minutesTextView.text = timeMetricToString(new)}
    private var seconds: Int by Delegates.observable(0) { _, _, new -> binding.secondsTextView.text = timeMetricToString(new)}

    private fun timeMetricToString(value: Int): String {
        if (value < 10) {
            return "0$value"
        }
        return value.toString()
    }

    suspend fun startTimer() {
        if (isRunning) return
        isRunning = true

        while (isRunning) {
            if (seconds + 1 == 60) {
                seconds = 0
                if (minutes + 1 == 60) {
                    minutes = 0
                    hours++
                } else {
                    minutes++
                }
            } else {
                seconds++
            }

            delay(1000)
        }
    }

    fun stopTimer() {
        isRunning = false
    }

    fun resetTimer() {
        seconds = 0
        minutes = 0
        hours = 0
    }
}
