package edu.towson.cosc435.GLADZAH.todos.database

import android.content.Context
import androidx.room.Room
import edu.towson.cosc435.GLADZAH.todos.Todo
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoRepository
import java.util.ArrayList


class TodoDatabaseRepository(ctx: Context): ITodoRepository {

    private val todoList: java.util.ArrayList<Todo> = arrayListOf()
    private val db: TodoDatabase

    init {
        db = Room.databaseBuilder(
            ctx,
            TodoDatabase::class.java,
            "todos.db"
        ).allowMainThreadQueries().build()

        refreshingTodoList()

    }

    private var editing = false
    private var editingIdx = -1

    override fun getCount(): Int {
        return todoList.size
    }

    override fun getTodo(idx: Int): Todo {
        return todoList.get(idx)
    }

    override fun getTodos(): ArrayList<Todo> {
        return todoList
    }

    override fun deleteTodo(idx: Int) {
        val todo = todoList[idx]
        db.todoDao().deleteTodo(todo)
        refreshingTodoList()
    }

    override fun replaceTodo(idx: Int, todo: Todo) {
        db.todoDao().updateTodo(todo)
        refreshingTodoList()
    }

    override fun addTodo(todo: Todo) {
        if(editing) {
            //todoList[editingIdx] = todo
            db.todoDao().updateTodo(todo)
            editing = false
            editingIdx = -1

        } else {
            db.todoDao().addTodo(todo)
        }
        refreshingTodoList()
    }

    override fun editTodo(idx: Int): Todo {

        editingIdx = idx
        editing = true
        return todoList[idx]
    }


    private fun refreshingTodoList() {
        todoList.clear()
        val todos = db.todoDao().getTodos()
        todoList.addAll(todos)
    }
}

