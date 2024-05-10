package dmitry.mobilecourse.textanim

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityTextAnimationBinding

class TextAnimationActivity : AppCompatActivity() {

    private lateinit var movingText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTextAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movingText = binding.movingText
    }

    fun onTextClick(view: View) {
        ObjectAnimator.ofFloat()
    }


}