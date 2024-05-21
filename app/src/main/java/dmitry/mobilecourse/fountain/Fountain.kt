package dmitry.mobilecourse.fountain

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class ParticleView(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), SurfaceHolder.Callback, Runnable {

    private var thread: Thread? = null
    @Volatile
    private var running = false
    private val particles = mutableListOf<Particle>()
    private val paint = Paint()
    private var emitterX: Float = 0f
    private var emitterY: Float = 0f
    private var emitParticles = false

    init {
        holder.addCallback(this)
        paint.color = Color.RED
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        running = true
        thread = Thread(this)
        thread?.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        running = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                emitterX = event.x
                emitterY = event.y
                emitParticles = true
            }
            MotionEvent.ACTION_MOVE -> {
                emitterX = event.x
                emitterY = event.y
            }
            MotionEvent.ACTION_UP -> emitParticles = false
        }
        return true
    }

    private fun emitParticles() {
        if (emitParticles) {
            synchronized(particles) {
                for (i in 0 until 5) {
                    particles.add(Particle(emitterX, emitterY))
                }
            }
        }
    }

    override fun run() {
        while (running) {
            if (holder.surface.isValid) {
                val canvas: Canvas = holder.lockCanvas() ?: continue
                canvas.drawColor(Color.BLACK)

                emitParticles()

                synchronized(particles) {
                    val iterator = particles.iterator()
                    while (iterator.hasNext()) {
                        val particle = iterator.next()
                        if (particle.isAlive()) {
                            particle.update()
                            canvas.drawCircle(particle.x, particle.y, 5f, paint)
                        } else {
                            iterator.remove()
                        }
                    }
                }

                holder.unlockCanvasAndPost(canvas)
            }
        }
    }
}

class Particle(var x: Float, var y: Float) {
    var vx: Float = (Math.random().toFloat() * 4 - 2)
    var vy: Float = (Math.random().toFloat() * 4 - 2)
    var lifetime: Int = 100
    private val gravity = 0.1f

    fun update() {
        x += vx
        y += vy
        vy += gravity
        lifetime--
    }

    fun isAlive(): Boolean {
        return lifetime > 0
    }
}