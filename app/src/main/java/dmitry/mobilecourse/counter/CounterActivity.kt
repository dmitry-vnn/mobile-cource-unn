package dmitry.mobilecourse.counter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var counterText: TextView
    private lateinit var counter: ObservableCounter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterText = binding.counterText
        counter = ObservableCounter(10, onCountChangeCallback = ::onCounterChange)
    }

    private fun onCounterChange(counter: Int) {
        counterText.text = counter.toString()
    }

    fun onIncreaseButtonClick(view: View) = counter.increment()

    fun onResetButtonClick(view: View) = counter.reset()
}