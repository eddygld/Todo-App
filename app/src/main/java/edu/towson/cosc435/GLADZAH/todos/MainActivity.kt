package edu.towson.cosc435.GLADZAH.todos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc435.GLADZAH.todos.database.TodoDatabaseRepository
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoController
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(),ITodoController {

    override lateinit var todos: ITodoRepository
    var editingTodoIdx = -1




    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putParcelableArrayList(STATE_LIST, todos.getTodos())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState)

        // Restore state members from saved instance
        savedInstanceState.run {
            todos.getTodos().clear()
            todos.getTodos().addAll(savedInstanceState.getParcelableArrayList<Todo>(STATE_LIST) as ArrayList)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todos = TodoDatabaseRepository(applicationContext)

        val adapter = TodoAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addNewTodoBtn.setOnClickListener { launchNewTodoScreen() }
    }


    override fun deleteTodo(idx: Int) {
        todos.deleteTodo(idx)
    }

    override fun toggleTodo(idx: Int) {
        val todo = todos.getTodo(idx)
        val newTodo = todo.copy(isCompleted = !todo.isCompleted)
        todos.replaceTodo(idx, newTodo)
    }

    override fun editTodo(idx: Int) {
        val todo = todos.editTodo(idx)
        val intent = Intent(this, NewTodoActivity::class.java)
        intent.putExtra(NewTodoActivity.TODO_EXTRA_KEY, Gson().toJson(todo))
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
    }



    override fun launchNewTodoScreen() {
        val intent =  Intent(this, NewTodoActivity::class.java)
        startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    ADD_TODO_REQUEST_CODE -> {
                        val json: String? = data?.getStringExtra(NewTodoActivity.TODO_EXTRA_KEY)
                        if (json != null) {
                            val todo = Gson().fromJson<Todo>(json, Todo::class.java)
                            todos.addTodo(todo )
                            recyclerView.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED -> {

            }
        }
    }

    companion object{
        const val ADD_TODO_REQUEST_CODE = 1
        const val STATE_LIST = "LIST"
    }


}