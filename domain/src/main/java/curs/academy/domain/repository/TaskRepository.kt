package curs.academy.domain.repository

import curs.academy.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task : Task)

    suspend fun getAllTask(userId : String) : List<Task>

    suspend fun deleteTask(id : Int)

    suspend fun updateTaskText(newText : String, id : Int)

    suspend fun updateStateCompletedTask(newStateTaskCompleted : Boolean, id : Int)

    fun getAllTaskFlow(userId : String) : Flow<List<Task>>

}