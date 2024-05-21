package dmitry.mobilecourse.counter

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourse.databinding.ActivityTodolistBinding
import dmitry.mobilecourse.list.TodoListModel

class TodoListActivity : AppCompatActivity() {
    private lateinit var todolistText: TextView

    private lateinit var todoList: TodoListModel
    private lateinit var inputWordText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTodolistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todolistText = binding.todolistText
        inputWordText = binding.inputText
        todoList = TodoListModel(::onTodoListChange)
    }

    private fun onTodoListChange(actualList: List<String>) {
        todolistText.text = actualList.joinToString()
    }

    fun onAddWordButtonClick(view: View) {
        val text = inputWordText.text.toString()

        if (!text.isWord()) {
            return
        }

        var word = text.toLowerCase()
        if (todoList.isEmpty()) {
            word = word.replaceFirstChar { word.first().toUpperCase() }
        }

        todoList += word
    }


    fun onRemoveLastWordButtonClick(view: View) {
        todoList.removeLast()
    }

}

private fun String.isWord(): Boolean {
    forEach {
        if (!it.isLetter()) {
            return false
        }
    }

    return isNotEmpty()
}
