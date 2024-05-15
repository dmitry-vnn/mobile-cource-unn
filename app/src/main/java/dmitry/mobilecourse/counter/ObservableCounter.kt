package dmitry.mobilecourse.counter

import kotlin.properties.Delegates

class ObservableCounter(private val defaultValue: Int = 0, onCountChangeCallback: (Int) -> Unit) : Counter {

    override var count: Int by Delegates.observable(0) {_, _, newValue -> onCountChangeCallback(newValue) }
        private set

    init {
        count = defaultValue
    }

    override fun reset() {
        count = defaultValue
    }

    override fun increment() {
        count++
    }

}