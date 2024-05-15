package dmitry.mobilecourse.list

class TodoListModel(
    private val onTodoListChangeCallback: (List<String>) -> Unit
) {

    private val todoList: MutableList<String> = mutableListOf()

    operator fun plusAssign(element: String) {
        todoList += element
        onTodoListChangeCallback(todoList)
    }

    fun removeLast() {
        todoList.removeLastOrNull()
        onTodoListChangeCallback(todoList)
    }

}