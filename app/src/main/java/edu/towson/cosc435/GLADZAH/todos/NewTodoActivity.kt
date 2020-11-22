package edu .towson.cosc435.GLADZAH.todos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoController
import kotlinx.android.synthetic.main.activity_new_todo.*
import java.util.*

class NewTodoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        saveBtn.setOnClickListener{
            val editingJson: String? = intent?.getStringExtra(TODO_EXTRA_KEY)
            val editingTodo = Gson().fromJson<Todo>(editingJson, Todo::class.java)
            val id = editingTodo?.id ?: UUID.randomUUID()
            val intent = Intent()
            val title = titleInput.editableText.toString()
            val text = textInput.editableText.toString()
            val isCompleted = isCompletedCb.isChecked
            val date = Date().toString()
            val todo = Todo(id, title, text, isCompleted, date)
            val json = Gson().toJson(todo)
            intent.putExtra(TODO_EXTRA_KEY, json)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }




//    override fun onClick(view: View?) {
//        when(view?.id) {
//            R.id.saveBtn -> {
//                val intent = Intent()
//                val id = UUID.randomUUID()
//                val title = titleInput.editableText.toString()
//                val text = textInput.editableText.toString()
//                val isCompleted = isCompletedCb.isChecked
//                val date = Date().toString()
//                val todo = Todo(id, title, text, isCompleted, date)
//                val json = Gson().toJson(todo)
//                intent.putExtra(TODO_EXTRA_KEY, json)
//                setResult(Activity.RESULT_OK, intent)
//                finish()
//            }
//        }
//    }


    companion object{
        val TODO_EXTRA_KEY = "TODO"
    }

}