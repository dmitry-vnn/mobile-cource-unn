package dmitry.mobilecourse.sprites

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityNinjaFightBinding

class NinjaFightActivity: AppCompatActivity() {

    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var binding: ActivityNinjaFightBinding
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinjaFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimationImage()
    }

    private fun initAnimationImage() {
        val animationImage = binding.animationImage

        animationImage.setBackgroundResource(R.drawable.sprite_animation)
        animationDrawable = animationImage.background as AnimationDrawable
    }

    fun onPlayPauseButtonClick(view: View) {
        if (!isPlaying) {
            animationDrawable.start()
            isPlaying = true
            binding.playPauseButton.text = "Pause"
        } else {
            animationDrawable.stop()
            isPlaying = false
            binding.playPauseButton.text = "Play"
        }
    }

}