package dmitry.mobilecourse.counter

fun main() {
    val metaCounter = MetaCounter()
    println(metaCounter.count)

    for (i in 0..<10) {
        metaCounter.increment()
    }

    println(metaCounter.count)

    metaCounter.reset()
    println(metaCounter.count)
}
