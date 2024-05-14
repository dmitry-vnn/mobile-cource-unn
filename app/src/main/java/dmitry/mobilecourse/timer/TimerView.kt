package dmitry.mobilecourse.timer

import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import dmitry.mobilecourse.databinding.CustomTimerViewBinding

class CustomTimerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding = CustomTimerViewBinding.inflate(LayoutInflater.from(context))

    private var isRunning: Boolean = false
    private var startTime: Long = 0
    private val handler: Handler = Handler()

    init {
        binding.hoursTextView .text = "00"
        binding.minutesTextView.text = "00"
        binding.secondsTextView.text = "00"
    }

    fun startTimer() {
        if (!isRunning) {
            isRunning = true
            startTime = SystemClock.elapsedRealtime()
            handler.post(updateTimer)
        }
    }

    fun stopTimer() {
        if (isRunning) {
            isRunning = false
            handler.removeCallbacks(updateTimer)
        }
    }

    private val updateTimer: Runnable = object : Runnable {
        override fun run() {
            val currentTime = SystemClock.elapsedRealtime() - startTime
            val hours = (currentTime / 3600000).toInt()
            val minutes = (currentTime % 3600000 / 60000).toInt()
            val seconds = (currentTime % 3600000 % 60000 / 1000).toInt()

            binding.hoursTextView .text = String.format("%02d", hours)
            binding.minutesTextView.text = String.format("%02d", minutes)
            binding.secondsTextView.text = String.format("%02d", seconds)

            if (isRunning) {
                handler.postDelayed(this, 1000)
            }
        }
    }
}

fun startTimer(view: CustomTimerView, start: Boolean) {
    if (start) {
        view.startTimer()
    }
}

fun stopTimer(view: CustomTimerView, stop: Boolean) {
    if (stop) {
        view.stopTimer()
    }
}