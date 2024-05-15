package dmitry.mobilecourse.counter

import kotlin.reflect.full.createInstance

class MetaCounter: Counter {

    private val counterInstance by lazy {
        CounterImpl::class.createInstance()
    }

    override val count: Int
        get() = CounterImpl::count.get(counterInstance)

    override fun reset() {
        CounterImpl::reset.invoke(counterInstance)
    }

    override fun increment() {
        CounterImpl::increment.invoke(counterInstance)
    }


}
