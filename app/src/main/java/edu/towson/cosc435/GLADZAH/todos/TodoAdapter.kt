package edu.towson.cosc435.GLADZAH.todos

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimationController
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoController
import kotlinx.android.synthetic.main.todo_view.view.*

class TodoAdapter(private val controller: ITodoController):RecyclerView.Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_view, parent, false)
        val viewHolder = TodoViewHolder(itemView)

        itemView.root.setOnLongClickListener {

            val dialogBuilder = AlertDialog.Builder(parent.context)

            val position = viewHolder.adapterPosition

            dialogBuilder.setMessage("Do you want to delete this item?")
                .setCancelable(false)
                .setPositiveButton("Yes", DialogInterface.OnClickListener {
                        dialog, id -> controller.deleteTodo(position)
                        this.notifyItemRemoved(position)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Alert")
            alert.show()

            true
        }

        itemView.isCompletedCheck.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.toggleTodo(position)
            this.notifyItemChanged(position)
        }

        itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.editTodo(position)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = controller.todos.getTodo(position)
        holder.bindTodo(todo)

    }

    override fun getItemCount(): Int {
        return controller.todos.getCount()

    }
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindTodo(todo: Todo) {
        itemView.todoTitle.text = todo.title
        itemView.todoContent.text = todo.contents
        itemView.isCompletedCheck.isChecked = todo.isCompleted
        itemView.createdDate.text = todo.dateCreated

    }

}