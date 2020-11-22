package edu.towson.cosc435.GLADZAH.todos.interfaces

import edu.towson.cosc435.GLADZAH.todos.Todo

interface ITodoController {

    fun deleteTodo(idx: Int)
    fun toggleTodo(idx: Int)
    fun launchNewTodoScreen()
    val todos: ITodoRepository
    fun editTodo(idx: Int)


}