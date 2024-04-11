package dmitry.mobilecourseunnlab3

import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourseunnlab3.databinding.ActivityTestingBinding

class TestingActivity : AppCompatActivity() {

    private val testing = listOf(
        Question("Вопрос 1", setOf("Ответ 1", "Ответ 2")),
        Question("Вопрос 2", setOf("Ответ A", "Ответ B", "Ответ C", "Ответ D")),
        Question("Вопрос 3", setOf("Да", "Нет")),
    )

    private lateinit var binding: ActivityTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initializeTesting()
    }

    private fun initializeTesting() {
        val listView = binding.listView
        val arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, testing.map(Question::name))

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            openContextMenu(view)
        }

        listView.setOnCreateContextMenuListener { menu, v, menuInfo ->
            val adapterContextMenuInfo = menuInfo as AdapterView.AdapterContextMenuInfo
            testing[adapterContextMenuInfo.position].answers.forEach {
                menu?.add(it)

            }
        }
    }
}

class Question(val name: String, val answers: Set<String>)