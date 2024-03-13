package dmitry.mobilecourseunnlab3

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class TwoSquareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            ThreeSquares()
        }
    }
}

@Preview
@Composable
fun ThreeSquares() {

    val side = 150.dp

    Box(
        Modifier
            .width(side)
            .height(side)
            .background(color = Color.Red)
    )

    Box(
        Modifier
            .padding(start = side, top = side / 2)
            .width(side)
            .height(side)
            .background(color = Color.Green)
    )

    Box(
        modifier = Modifier
            .padding(start = side * 1.5f)
            .width(side)
            .height(side)
            .background(color = Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Квадрат", color = Color.White)
    }
}

@Preview
@Composable
fun RowAndCol() {
    val side = 100.dp
    val spacing = 25.dp
    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Square(side, Color.Red)
            Square(side, Color.Magenta)
        }

        Square(side, Color.Green)

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing)
        ) {
            Square(side, Color.Blue)
            Square(side, Color.Black)
        }
    }
}

@Preview
@Composable
fun GridSample() {

    val side = 100.dp
    val spacing = 25.dp

    val squaresColors = Color.run {
        arrayOf(
            Red, Magenta, Green, Blue, White, Black
        )
    }

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        columns = GridCells.Fixed(3)
    ) {
        items(squaresColors) { Square(side, color = it) }
    }
}

@Preview
@Composable
fun TransformSample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val sidePx = 150.dp.toPx()
        drawRect(
            color = Color.Black,
            size = Size(sidePx, sidePx),
        )

        val offset = Offset(sidePx * 2, sidePx)
        rotate(degrees = 45f, pivot = offset) {
            scale(scaleX = 0.5f, scaleY = 2f, pivot = offset) {
                drawRect(
                    color = Color.Black,
                    size = Size(sidePx, sidePx),
                    topLeft = offset
                )
            }
        }
    }
}

@Composable
fun Square(sideSize: Dp, color: Color) {
    Box(
        Modifier
            .height(sideSize)
            .width(sideSize)
            .background(color)

    )
}
