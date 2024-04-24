package dmitry.mobilecourse.interactivelist

import android.app.ActionBar.LayoutParams
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import androidx.gridlayout.widget.GridLayout
import dmitry.mobilecourse.databinding.ActivityInteractiveListModelBinding

class InteractiveListModelActivity : AppCompatActivity(), RectangleListModel.Observer {

    private lateinit var column: GridLayout
    private lateinit var rectangleListModel: RectangleListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityInteractiveListModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        column = binding.column
        rectangleListModel = RectangleListModel(this)
    }

    fun onAddButtonClick(view: View) {
        rectangleListModel.addRectangle(Rectangle())
    }

    override fun onRectangleAdded(rectangle: Rectangle) {
        val rectangleView = createRectangleView(rectangle)
        val textView = createTextView(rectangle)

        val frameLayout = FrameLayout(this)

        frameLayout.setPadding(0, 15.dp.value.toInt(), 0, 0)
        frameLayout.addView(rectangleView)
        frameLayout.addView(textView)

        frameLayout.setOnClickListener { rectangleListModel.removeRectangle(rectangle) }
        frameLayout.tag = rectangle

        column.addView(frameLayout)
    }

    override fun onRectangleRemoved(rectangle: Rectangle) {
        for (i in 0..<column.childCount) {
            if (column.getChildAt(i).tag == rectangle) {
                column.removeViewAt(i)
                return
            }
        }
    }

    private fun createTextView(rectangle: Rectangle): TextView {
        val textView = TextView(this)
        textView.text = "Элемент ${rectangle.id}"

        val textLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.gravity = Gravity.CENTER
        textView.layoutParams = textLayoutParams

        return textView
    }

    private fun createRectangleView(rectangle: Rectangle): View {
        val rectangleView = View(this)
        rectangleView.setBackgroundColor(Color.CYAN)

        val layoutParams = LayoutParams(0, 0)
        layoutParams.height = 250.dp.value.toInt()
        layoutParams.width = GridLayout.LayoutParams.MATCH_PARENT

        rectangleView.layoutParams = layoutParams
        return rectangleView
    }

}

class RectangleListModel(
    private val observer: Observer
) {

    interface Observer {
        fun onRectangleAdded(rectangle: Rectangle)
        fun onRectangleRemoved(rectangle: Rectangle)
    }

    private val rectangles = hashMapOf<Int, Rectangle>()

    fun addRectangle(rectangle: Rectangle) {
        rectangles += rectangle.id to rectangle
        observer.onRectangleAdded(rectangle)
    }

    fun removeRectangle(rectangle: Rectangle) {
        rectangles -= rectangle.id
        observer.onRectangleRemoved(rectangle)
    }

}

class Rectangle {

    val id: Int = idCounter++

    private companion object {
        var idCounter = 1
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}