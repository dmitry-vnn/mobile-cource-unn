package dmitry.mobilecourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityWebBinding.inflate(layoutInflater)

        binding.webView.loadUrl("https://github.com")

        setContentView(binding.root)
    }
}