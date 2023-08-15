package curs.academy.domain.usecases.task

import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetFlowAllTaskUseCase(private val taskRepository: TaskRepository) {

    fun execute(userId : String) : Flow<List<Task>> {
        return taskRepository.getAllTaskFlow(userId)
    }

}