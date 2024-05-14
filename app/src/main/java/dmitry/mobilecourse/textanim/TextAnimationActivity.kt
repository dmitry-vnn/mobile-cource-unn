package dmitry.mobilecourse.textanim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityTextAnimationBinding

class TextAnimationActivity : AppCompatActivity() {

    private lateinit var movingText: TextView

    private lateinit var animator: AnimatorSet

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTextAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movingText = binding.movingText
        movingText.setOnTouchListener(::onTextTouch)

        animator = createDirectAnimator()
    }

    private fun createDirectAnimator(): AnimatorSet {
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
        val moveAnimation = ObjectAnimator.ofFloat(
            movingText,
            "translationY",
            0f,
            screenHeight - 350
        ).apply { duration = 2000 }


        val rotateAnimation = ObjectAnimator.ofFloat(movingText, "rotation", 0f, 180f).apply { duration = 2000 }

        val changeColorAnimation = ObjectAnimator.ofArgb(
            movingText, "textColor",
            Color.BLACK,
            Color.BLUE
        ).apply { duration = 2000 }

        val animator = AnimatorSet().apply {
            playTogether(moveAnimation, rotateAnimation, changeColorAnimation)
        }

        return animator
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onTextTouch(view: View?, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                animator.start()
            }

            MotionEvent.ACTION_UP -> {
                animator.reverse()

            }
        }
        return true
    }

}