package curs.academy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import curs.academy.domain.models.Task


@Entity(tableName = "task_table")
data class TaskModel (
    val title : String,
    val text : String,
    val dateOfCreation : Long,
    val dateOfFutureExecution : Long,
    val taskCompleted : Boolean,
    val userId : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
){
    fun toTask():Task = Task(
        title,
        text,
        dateOfCreation,
        dateOfFutureExecution,
        taskCompleted,
        userId,
        id
    )

    companion object{
        fun map(task: Task): TaskModel = TaskModel(task.title, task.text, task.dateOfCreation,
            task.dateOfFutureExecution, task.taskCompleted, task.userId)
    }
}