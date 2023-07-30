package curs.academy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import curs.academy.data.models.TaskModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskModel)

    @Query("SELECT * FROM task_table WHERE userId = :userId")
    fun getAllTask(userId : Int) : List<TaskModel>

    @Query("DELETE FROM task_table WHERE id = :id")
    fun deleteTask(id : Int)

    @Query("UPDATE task_table SET text = :newText WHERE id = :id")
    fun updateTaskText(newText : String, id : Int)

    @Query("UPDATE task_table SET taskCompleted = :newStateTaskCompleted WHERE id = :id")
    fun updateStateCompletedTask(newStateTaskCompleted : Boolean, id : Int)

    @Query("SELECT * FROM task_table WHERE userId = userId")
    fun getAllTaskFlow(userId : Int) : Flow<List<TaskModel>>
}