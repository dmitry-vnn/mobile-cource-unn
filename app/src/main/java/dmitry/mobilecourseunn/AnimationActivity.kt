package dmitry.mobilecourseunn

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity

class AnimationActivity : AppCompatActivity() {

    private lateinit var fallingAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fallingAnimation = AnimationUtils.loadAnimation(this, R.anim.square_falling)

        setContentView(R.layout.activity_animate)
    }


    fun onAnimateButtonClick(view: View) {
        val square = findViewById<View>(R.id.view_square)
        square.startAnimation(fallingAnimation)
    }
}