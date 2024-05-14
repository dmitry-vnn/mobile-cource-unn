package dmitry.mobilecourse.timer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startStopButton.setOnClickListener {
            isTimerRunning = !isTimerRunning
            binding.startStopButton.text = if (isTimerRunning) "Стоп" else "Старт"
            binding.customTimerView.apply {
                if (isTimerRunning) {
                    startTimer()
                } else {
                    stopTimer()
                }
            }
        }
    }
}