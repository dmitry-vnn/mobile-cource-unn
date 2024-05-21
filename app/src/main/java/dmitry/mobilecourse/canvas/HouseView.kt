package dmitry.mobilecourse.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class HouseView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private lateinit var center: Pair<Float, Float>

    private val houseSide = 600f

    private val skyPaint = Paint().apply {
        color = Color.CYAN
        style = Paint.Style.FILL
    }

    private val groundPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    private val housePaint = Paint().apply {
        color = Color.rgb(229,87,101)
        style = Paint.Style.FILL
    }

    private val roofPaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val roofPipePaint = Paint().apply {
        color = Color.rgb(145,80,8)
        style = Paint.Style.FILL
    }

    private val roofPath = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        center = width.toFloat() / 2 to height.toFloat() / 2

        val skyHeightPercentage = 0.5f
        // Draw sky
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat() * skyHeightPercentage, skyPaint)

        // Draw ground
        canvas.drawRect(0f, height.toFloat() * (1 - skyHeightPercentage), width.toFloat(), height.toFloat(), groundPaint)

        drawHouse(canvas)

        drawRoof(canvas)
    }

    private fun drawRoof(canvas: Canvas) {

        val baseLength = 1.3f * houseSide // Длина основания
        val height = houseSide / 2 // Высота треугольника

        val vertexX = center.first // X-координата вершины треугольника
        val vertexY = center.second - houseSide // Y-координата вершины треугольника

        // Вычисляем координаты остальных двух точек основания треугольника
        val baseStartX = vertexX - baseLength / 2
        val baseEndX = vertexX + baseLength / 2
        val baseY = vertexY + height

        roofPath.apply {
            moveTo(baseStartX, baseY) // Перемещаемся в начальную точку основания
            lineTo(baseEndX, baseY) // Рисуем линию до конечной точки основания
            lineTo(vertexX, vertexY) // Рисуем линию до вершины треугольника
            close() // Замыкаем путь, чтобы получить треугольник
        }

        drawRoofPipe(baseLength, height, vertexX, vertexY, canvas)

        canvas.drawPath(roofPath, roofPaint) // Рисуем треугольник
    }

    private fun drawRoofPipe(
        baseLength: Float, height: Float,
        vertexX: Float, vertexY: Float, canvas: Canvas
    ) {

        val pipeLineStartX = baseLength / 4 + vertexX
        val pipeLineEndY = vertexY + height

        val pipeWidth = 100

        canvas.drawRect(
            pipeLineStartX - pipeWidth / 2, pipeLineEndY - height,
            pipeLineStartX + pipeWidth / 2, pipeLineEndY,
             roofPipePaint
        )
    }

    private fun drawHouse(canvas: Canvas) {
        val (centerX, centerY) = center

        val halfSide = houseSide / 2

        val startX = centerX - halfSide
        val startY = centerY - halfSide

        val endX = centerX + halfSide
        val endY = centerY + halfSide

        canvas.drawRect(
            startX, startY,
            endX, endY,
            housePaint
        )
    }

}