package dmitry.mobilecourse.rectangles

import android.app.ActionBar.LayoutParams
import android.graphics.Color.*
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import androidx.gridlayout.widget.GridLayout
import dmitry.mobilecourse.databinding.ActivityListModelSampleBinding

class ListModelSampleActivity : AppCompatActivity(),
    RectangleListModel.Observer {

    private lateinit var column: GridLayout
    private lateinit var rectangleListModel: RectangleListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListModelSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        column = binding.column
        rectangleListModel = RectangleListModel(this)

        rectangleListModel.addRectangle(Rectangle(CYAN, "CYAN"))
        rectangleListModel.addRectangle(Rectangle(BLACK, "BLACK"))
        rectangleListModel.addRectangle(Rectangle(RED, "RED"))
    }

    override fun onRectangleAdded(rectangle: Rectangle) {
        val rectangleView = createRectangleView(rectangle)
        val textView = createTextView(rectangle)

        val frameLayout = FrameLayout(this)

        frameLayout.setPadding(0, column.paddingLeft, 0, 0)
        frameLayout.addView(rectangleView)
        frameLayout.addView(textView)

        column.addView(frameLayout)
    }

    private fun createTextView(rectangle: Rectangle): TextView {
        val textView = TextView(this)
        textView.text = rectangle.text

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
        rectangleView.setBackgroundColor(rectangle.backgroundColor)

        val layoutParams = LayoutParams(0, 0)
        layoutParams.height = 250.dp.value.toInt()
        layoutParams.width = GridLayout.LayoutParams.MATCH_PARENT

        rectangleView.layoutParams = layoutParams
        return rectangleView
    }


}

class Rectangle(val backgroundColor: Int, val text: String)

class RectangleListModel(
    private val observer: Observer
) {

    interface Observer {
        fun onRectangleAdded(rectangle: Rectangle)
    }

    private val rectangles = mutableListOf<Rectangle>()

    fun addRectangle(rectangle: Rectangle) {
        rectangles += rectangle
        observer.onRectangleAdded(rectangle)
    }

}
