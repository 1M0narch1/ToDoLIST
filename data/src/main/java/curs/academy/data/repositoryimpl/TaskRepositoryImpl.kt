package curs.academy.data.repositoryimpl

import curs.academy.data.database.dao.TaskDao
import curs.academy.data.models.TaskModel
import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class TaskRepositoryImpl(val taskDao: TaskDao) : TaskRepository {

    private val job = SupervisorJob()
    private val taskScope = CoroutineScope(job+Dispatchers.IO)

    override suspend fun insertTask(task: Task) {
        taskScope.launch {
            taskDao.insertTask(TaskModel.map(task))
        }
    }

    override suspend fun getAllTask(): List<Task> {
        return taskScope.async {
            taskDao.getAllTask().map { it.toTask() }
        }.await()
    }

    override suspend fun deleteTask(id: Int) {
        taskScope.launch {
            taskDao.deleteTask(id)
        }
    }

    override suspend fun updateTaskText(newText: String, id: Int) {
        taskScope.launch {
            taskDao.updateTaskText(newText, id)
        }
    }

    override suspend fun updateStateCompletedTask(newStateTaskCompleted: Boolean, id: Int) {
        taskScope.launch {
            taskDao.updateStateCompletedTask(newStateTaskCompleted, id)
        }
    }

    override fun getAllTaskFlow(): Flow<List<Task>> {
        return taskDao.getAllTaskFlow().flatMapConcat { list ->
            flowOf(list.map { it.toTask() })
        }
    }
}