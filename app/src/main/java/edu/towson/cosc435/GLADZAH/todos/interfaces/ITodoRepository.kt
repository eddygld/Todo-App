package edu.towson.cosc435.GLADZAH.todos.interfaces

import edu.towson.cosc435.GLADZAH.todos.Todo
import java.util.ArrayList

interface ITodoRepository {
    fun getCount(): Int
    fun getTodo(idx: Int): Todo
    fun getTodos(): ArrayList<Todo>
    fun deleteTodo(idx: Int)
    fun replaceTodo(idx: Int, todo: Todo)
    fun addTodo(todo: Todo)
    fun editTodo(idx: Int) : Todo
}