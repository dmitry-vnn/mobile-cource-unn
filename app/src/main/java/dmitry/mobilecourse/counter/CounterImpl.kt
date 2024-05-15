package dmitry.mobilecourse.counter

class CounterImpl : Counter {

    override var count: Int = 0
        private set

    override fun reset() {
        count = 0
    }

    override fun increment() {
        count++
    }

}