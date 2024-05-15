package dmitry.mobilecourse.counter

interface Counter {
    val count: Int
    fun reset()
    fun increment()
}