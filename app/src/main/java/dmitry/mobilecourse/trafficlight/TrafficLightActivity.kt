package dmitry.mobilecourse.trafficlight

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import dmitry.mobilecourse.R
import dmitry.mobilecourse.databinding.ActivityTrafficlightBinding
import dmitry.mobilecourse.trafficlight.TrafficLightModel.TrafficLightState
import dmitry.mobilecourse.trafficlight.TrafficLightModel.TrafficLightState.*
import kotlinx.coroutines.launch

class TrafficLightActivity: AppCompatActivity() {

    private lateinit var trafficLightModel: TrafficLightModel

    private lateinit var character: ImageView

    private lateinit var stop: CardView
    private lateinit var go: CardView
    private lateinit var wait: CardView

    private lateinit var movingAnimation: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTrafficlightBinding.inflate(layoutInflater)

        setContentView(binding.root)

        go = binding.go
        wait = binding.wait
        stop = binding.stop
        character = binding.characterImage

        movingAnimation = AnimationUtils.loadAnimation(this, R.anim.character_moving)

        trafficLightModel = TrafficLightModel(::onTrafficLightSwitch, 5, 5)

        lifecycleScope.launch {
            trafficLightModel.start()
        }
    }

    private fun onTrafficLightSwitch(oldState: TrafficLightState, newState: TrafficLightState) {
        updateTrafficLight(oldState, newState)
        if (newState == GO) {
            animateCharacter()
        }
    }

    private fun animateCharacter() {
        character.startAnimation(movingAnimation)
    }

    private fun updateTrafficLight(
        oldState: TrafficLightState,
        newState: TrafficLightState
    ) {
        val oldView = getViewByState(oldState)
        val newView = getViewByState(newState)

        oldView.setCardBackgroundColor(Color.BLACK)
        newView.setCardBackgroundColor(newState.color)
    }

    private fun getViewByState(state: TrafficLightState): CardView {
        return when (state) {
            STOP -> stop
            WAIT -> wait
            GO -> go
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        trafficLightModel.stop()
    }


}