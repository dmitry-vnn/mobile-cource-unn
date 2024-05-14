package dmitry.mobilecourse.textanim

import android.R.color.holo_blue_dark
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityTextAnimationBinding

class TextAnimationActivity : AppCompatActivity() {

    private lateinit var movingText: TextView

    private lateinit var directAnimator: AnimatorSet
    private lateinit var reverseAnimator: AnimatorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTextAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movingText = binding.movingText
        movingText.setOnTouchListener(::onTextTouch)

        directAnimator = createDirectAnimator()
        reverseAnimator = createReverseAnimator()
    }

    private fun createDirectAnimator(): AnimatorSet {
        val moveAnimation = ObjectAnimator.ofFloat(movingText, "translationY", 0f, 1500f).apply {
            duration = 2000
        }

        val rotateAnimation = ObjectAnimator.ofFloat(movingText, "rotation", 0f, 180f).apply {
            duration = 2000
        }

        val changeColorAnimation = ObjectAnimator.ofArgb(
            movingText, "textColor",
            Color.BLACK,
            getResources().getColor(holo_blue_dark)
        ).apply {
            duration = 2000
        }

        val animator = AnimatorSet().apply {
            playTogether(moveAnimation, rotateAnimation, changeColorAnimation)
        }

        return animator
    }

    private fun createReverseAnimator(): AnimatorSet {
        val moveAnimation = ObjectAnimator.ofFloat(movingText, "translationY", 0f).apply {
            duration = 1000
        }

        val rotateAnimation = ObjectAnimator.ofFloat(movingText, "rotation", 0f).apply {
            duration = 1000
        }

        val changeColorAnimation = ObjectAnimator.ofArgb(
            movingText, "textColor",
            Color.BLACK
        ).apply {
            duration = 1000
        }

        val animator = AnimatorSet().apply {
            playTogether(moveAnimation, rotateAnimation, changeColorAnimation)
        }

        return animator
    }

    private fun onTextTouch(view: View?, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                startDirectAnimation()
            }
            MotionEvent.ACTION_UP -> startReverseAnimation()
        }
        return true
    }

    @SuppressLint("Recycle")
    private fun startDirectAnimation() {
        if (directAnimator.isPaused) {
            directAnimator.cancel()
        }

        if (reverseAnimator.isRunning) {
            reverseAnimator.cancel()
        }

        directAnimator.start()
    }

    private fun startReverseAnimation() {
        if (directAnimator.isRunning) {
            directAnimator.cancel()
        }
        reverseAnimator.start()
    }


}