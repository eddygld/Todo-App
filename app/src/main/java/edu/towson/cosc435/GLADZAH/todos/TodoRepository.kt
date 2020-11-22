package edu.towson.cosc435.GLADZAH.todos


import android.os.Parcel
import android.os.Parcelable
import edu.towson.cosc435.GLADZAH.todos.interfaces.ITodoRepository
import java.util.*
import kotlin.collections.ArrayList

class TodoRepository() : ITodoRepository, Parcelable {

    private val todos: java.util.ArrayList<Todo> = arrayListOf()

    init {

        todos.addAll((0..10).map {
            Todo(
                UUID.randomUUID(),
                "Title${it}",
                "Content${it}",
                isCompleted = false,
                Date().toString()
            )
        })
    }



    private var editing = false
    private var editingIdx = -1

    constructor(parcel: Parcel) : this() {
        editing = parcel.readByte() != 0.toByte()
        editingIdx = parcel.readInt()
    }


    override fun getCount(): Int {
        return todos.size
    }

    override fun getTodo(idx: Int): Todo {
        if (idx < 0) return todos[0]
        if (idx >= getCount()) return todos[todos.size - 1]
        return todos[idx]
    }

    override fun getTodos(): java.util.ArrayList<Todo> {
        return todos
    }

    override fun deleteTodo(idx: Int) {
        if (idx < 0 || idx >= getCount()) throw Exception("Index out of bounds")
        todos.removeAt(idx)
    }

    override fun replaceTodo(idx: Int, todo: Todo) {
        todos[idx] = todo
    }

    override fun addTodo(todo: Todo) {
        if(editing) {
            todos[editingIdx] = todo
            editing = false
            editingIdx = -1
        } else {
            todos.add(todo)
        }
    }

    override fun editTodo(idx: Int): Todo {
        editingIdx = idx
        editing = true
        return todos[idx]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (editing) 1 else 0)
        parcel.writeInt(editingIdx)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoRepository> {
        override fun createFromParcel(parcel: Parcel): TodoRepository {
            return TodoRepository(parcel)
        }

        override fun newArray(size: Int): Array<TodoRepository?> {
            return arrayOfNulls(size)
        }
    }


}
