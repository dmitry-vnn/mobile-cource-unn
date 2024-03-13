package dmitry.mobilecourseunnlab3

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourseunnlab3.databinding.ActivityClickerBinding

class ClickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClickerBinding

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClickerBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    fun onPlusButtonClick(view: View) {
        count++
        updateCounter()
    }

    fun onMinusButtonClick(view: View) {
        if (count > 0) {
            count--
            updateCounter()
        }
    }

    fun onResetButtonClick(view: View) {
        count = 0
        updateCounter()
    }

    private fun updateCounter() {
        binding.textCount.text = count.toString()
    }
}
