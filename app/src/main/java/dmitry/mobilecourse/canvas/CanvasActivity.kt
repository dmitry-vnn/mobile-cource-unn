package dmitry.mobilecourse.canvas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityCanvasBinding

class CanvasActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}