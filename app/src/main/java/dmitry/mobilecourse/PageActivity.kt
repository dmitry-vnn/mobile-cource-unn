package dmitry.mobilecourse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityPageBinding


class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding

    companion object {
        private var stackDepth = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val stackDepthText = binding.stackDepthText

        stackDepthText.text = "Глубина стека: $stackDepth"
    }

    fun onNextButtonClick(view: View) {
        stackDepth++
        val intent = Intent(this, javaClass)
        startActivity(intent)
    }

    fun onBackButtonClick(view: View) {
        if (stackDepth > 0) {
            stackDepth--
            finish()
        }
    }

}