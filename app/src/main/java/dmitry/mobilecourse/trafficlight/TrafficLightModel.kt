package dmitry.mobilecourse.trafficlight

import android.graphics.Color
import dmitry.mobilecourse.trafficlight.TrafficLightModel.TrafficLightState.*
import kotlinx.coroutines.delay
import kotlin.properties.Delegates

class TrafficLightModel(
    private val stateObserverCallback: (oldState: TrafficLightState, newState: TrafficLightState) -> Unit,
    stopSignalDelay: Int = 10,
    goSignalDelay: Int = 20,
) {

    private val lightsDelays = mapOf(
        STOP to stopSignalDelay,
        WAIT to 3,
        GO to goSignalDelay
    )

    private var state: TrafficLightState by Delegates.observable(STOP) { _, oldState, newState ->
        stateObserverCallback(oldState, newState)
    }

    private var working = false

    suspend fun start() {
        working = true

        var isDirectOrder = true

        state = STOP

        while (working) {

            delay(lightsDelays[state]!! * 1000L)

            when (state) {
                STOP, GO -> state = WAIT
                WAIT -> {
                    state = if (isDirectOrder) GO else STOP
                    isDirectOrder = !isDirectOrder
                }
            }
        }
    }

    fun stop() {
        working = false
    }


    enum class TrafficLightState(val color: Int) {
        STOP(Color.RED), WAIT(Color.YELLOW), GO(Color.GREEN)
    }
}
