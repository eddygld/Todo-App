package edu.towson.cosc435.GLADZAH.todos.database


import androidx.room.*
import edu.towson.cosc435.GLADZAH.todos.Todo
import java.util.*


@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("select id, title, contents, isCompleted, dateCreated from Todo")
    fun getTodos(): List<Todo>


    @Query("SELECT  id, title, contents, isCompleted, dateCreated FROM Todo where id LIKE :idx LIMIT 1")
    fun getTodo(idx: Int): Todo

}

class UUIDConverter {

    @TypeConverter
    fun fromString(uuid: String): UUID {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun toString(uuid: UUID): String {
        return uuid.toString()
    }
}

@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
