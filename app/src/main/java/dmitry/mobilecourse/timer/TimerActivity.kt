package dmitry.mobilecourse.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dmitry.mobilecourse.databinding.ActivityTimerBinding
import kotlinx.coroutines.launch

class TimerActivity : AppCompatActivity() {

    private lateinit var timerView: CustomTimerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timerView = binding.customTimerView

        binding.startButton.setOnClickListener {
            if (timerView.isRunning) {
                timerView.stopTimer()
            } else {
                lifecycleScope.launch {
                    timerView.startTimer()
                }
            }

            binding.startButton.text = if (timerView.isRunning) "Pause" else "Run"
        }

        binding.resetButton.setOnClickListener {
            timerView.resetTimer()
        }
    }
}