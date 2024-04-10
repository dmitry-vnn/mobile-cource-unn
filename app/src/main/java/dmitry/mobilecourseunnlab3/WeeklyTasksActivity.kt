package dmitry.mobilecourseunnlab3

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dmitry.mobilecourseunnlab3.databinding.ActivityWeeklyTasksBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class WeeklyTasksActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val weeklyTasks = listOf(
        Task("Пойти в качалку", LocalDate.of(2024, 5, 30)),
        Task("Устроиться в Гугл", LocalDate.of(2024, 6, 1)),
        Task("Забрать диплом", LocalDate.of(2024, 6, 2)),
        Task("Поехать на море", LocalDate.of(2024, 6, 1)),
        Task("Бла-бла", LocalDate.of(2024, 5, 30)),
    )
    private lateinit var binding: ActivityWeeklyTasksBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeeklyTasksBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initializeListView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initializeListView() {
        val groupedTasks = weeklyTasks.groupBy { it.date }
            .map { (date, tasks) -> GroupedTasks(date, tasks.map { it.name }) }

        binding.listView.adapter = ListViewTasksAdapter(this, groupedTasks)
    }
}


class Task(val name: String, val date: LocalDate)
class GroupedTasks(val date: LocalDate, val names: List<String>)

class ListViewTasksAdapter(context: Context, tasks: List<GroupedTasks>) :
    ArrayAdapter<GroupedTasks>(context, 0, tasks) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val task = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        }

        val namesText = itemView!!.findViewById<TextView>(R.id.names_text)
        val dateText = itemView.findViewById<TextView>(R.id.date_text)

        task?.let {
            namesText.text = it.names.joinToString(separator = "\n\n")
            dateText.text = it.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        }

        return itemView
    }

}