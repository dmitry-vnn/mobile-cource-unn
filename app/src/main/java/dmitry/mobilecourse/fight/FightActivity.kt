package dmitry.mobilecourse.fight

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.lifecycleScope
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityFightBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class FightActivity : AppCompatActivity() {

    private lateinit var firstPlayerAnimation: AnimationDrawable
    private lateinit var secondPlayerAnimation: AnimationDrawable

    private lateinit var binding: ActivityFightBinding

    private var isFirstPlayerActive = true

    private var fpHp: Int by Delegates.observable(100, ::onFpHpChanged)
    private var spHp: Int by Delegates.observable(100, ::onSpHpChanged)

    private var isRunning = true

    @SuppressLint("SetTextI18n")
    private fun onSpHpChanged(property: KProperty<*>, oldValue: Int, value: Int) {
        binding.spHealthText.text = "HP: ${maxOf(value, 0)}%"
        if (value <= 0) {
            endGame(true)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onFpHpChanged(property: KProperty<*>, oldValue: Int, value: Int) {
        binding.fpHealthText.text = "HP: ${maxOf(value, 0)}%"
        if (value <= 0) {
            endGame(false)
        }
    }

    private fun endGame(isFirstPlayerWinner: Boolean) {
        isRunning = false
        firstPlayerAnimation.stop()
        secondPlayerAnimation.stop()

        AlertDialog.Builder(this)
            .setTitle("Fight Game")
            .setMessage("${(if (isFirstPlayerWinner) "First" else "Second")} player is winner!!!")
            .show()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnTouchListener(::onTouch)

        initAnimation()
        startFightDetection()
        playAnimation()
    }

    private fun onPlayerCausedDamage(isFirstPlayerCaused: Boolean) {
        val delta = 5
        if (isFirstPlayerCaused) {
            spHp -= delta
        } else {
            fpHp -= delta
        }
    }

    private fun playAnimation() {
        firstPlayerAnimation.start()
        secondPlayerAnimation.start()
    }

    private fun startFightDetection() {
        lifecycleScope
        lifecycleScope.launch {

            val firstPlayerStartFrame = firstPlayerAnimation.current
            val secondPlayerStartFrame = secondPlayerAnimation.current

            detectPlayerFightFrame(firstPlayerStartFrame, secondPlayerStartFrame) { isFirstPlayerFight ->
                val fp = binding.firstPlayer
                val sp = binding.secondPlayer

                val halfSide = fp.width / 2

                val fpCenterX = fp.x + halfSide
                val spCenterX = sp.x + halfSide

                if (isFirstPlayerFight) {
                    if (fpCenterX in (spCenterX - 0.75 * halfSide)..spCenterX.toDouble()) {
                        onPlayerCausedDamage(true)
                        Log.i("FIGHT", "FP DAMAGED TO SP")
                    }
                } else if (spCenterX in fpCenterX.toDouble()..(fpCenterX + 0.75 * halfSide)) {
                    onPlayerCausedDamage(false)
                    Log.i("FIGHT", "SP DAMAGED TO FP")
                }
            }
        }
    }

    private suspend fun detectPlayerFightFrame(firstPlayerStartFrame: Drawable, secondPlayerStartFrame: Drawable, callback: (Boolean) -> Unit) {
        val neededIndex = 4

        var fpPreviousFrame = firstPlayerStartFrame
        var fpCurrentIndex = 0

        var spPreviousFrame = secondPlayerStartFrame
        var spCurrentIndex = 0

        while (isRunning) {
            delay(50L)
            val fpFrame = firstPlayerAnimation.current
            if (fpFrame != fpPreviousFrame)  {
                fpCurrentIndex++
                if (fpCurrentIndex == neededIndex) {
                    callback(true)
                }
                if (fpCurrentIndex == firstPlayerAnimation.numberOfFrames - 1) {
                    fpCurrentIndex = 0
                    fpPreviousFrame = firstPlayerStartFrame
                } else {
                    fpPreviousFrame = fpFrame
                }
            }

            val spFrame = secondPlayerAnimation.current
            if (spFrame != spPreviousFrame)  {
                spCurrentIndex++
                if (spCurrentIndex == neededIndex) {
                    callback(false)
                }
                if (spCurrentIndex == secondPlayerAnimation.numberOfFrames - 1) {
                    spCurrentIndex = 0
                    spPreviousFrame = secondPlayerStartFrame
                } else {
                    spPreviousFrame = spFrame
                }
            }
        }
    }

    private fun onTouch(view: View?, motionEvent: MotionEvent): Boolean {
        movePlayer(
            if (isFirstPlayerActive) binding.firstPlayer else binding.secondPlayer,
            motionEvent.x
        )
        return true
    }

    private fun movePlayer(player: GridLayout, toX: Float) {
        val animatorX = ObjectAnimator.ofFloat(
            player, "x",
            (toX - player.width / 2)
        )
        val animatorSet = AnimatorSet()
        animatorSet.play(animatorX)
        animatorSet.setDuration(500)
        animatorSet.start()
    }

    private fun initAnimation() {
        val firstPlayerImage = binding.firstPlayerImage

        firstPlayerImage.setBackgroundResource(R.drawable.direct_attack_anim)
        firstPlayerAnimation = firstPlayerImage.background as AnimationDrawable

        val secondPlayerImage = binding.secondPlayerImage

        secondPlayerImage.setBackgroundResource(R.drawable.reverse_attack_anim)
        secondPlayerAnimation = secondPlayerImage.background as AnimationDrawable
    }

    fun onSwitchButtonClick(view: View) {
        isFirstPlayerActive = !isFirstPlayerActive
    }
}